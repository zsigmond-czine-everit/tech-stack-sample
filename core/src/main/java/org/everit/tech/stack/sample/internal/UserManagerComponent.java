/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.tech.stack.sample.internal;

import java.util.ArrayList;
import java.util.List;

import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.ConfigurationPolicy;
import org.everit.osgi.ecm.annotation.Service;
import org.everit.osgi.ecm.annotation.ServiceRef;
import org.everit.osgi.ecm.extender.ECMExtenderConstants;
import org.everit.osgi.querydsl.support.QuerydslSupport;
import org.everit.osgi.transaction.helper.api.TransactionHelper;
import org.everit.tech.stack.sample.AddressManager;
import org.everit.tech.stack.sample.UserManager;
import org.everit.tech.stack.sample.dto.AddressDTO;
import org.everit.tech.stack.sample.dto.CreateUserDTO;
import org.everit.tech.stack.sample.dto.UserDTO;
import org.everit.tech.stack.sample.schema.qdsl.QAddress;
import org.everit.tech.stack.sample.schema.qdsl.QUser;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.path.StringPath;
import com.mysema.query.types.query.ListSubQuery;

import aQute.bnd.annotation.headers.ProvideCapability;

/**
 * Implementation of {@link UserManager}.
 */
@Component(configurationPolicy = ConfigurationPolicy.REQUIRE)
@ProvideCapability(ns = ECMExtenderConstants.CAPABILITY_NS_COMPONENT,
    value = ECMExtenderConstants.CAPABILITY_ATTR_CLASS + "=${@class}")
@Service
public class UserManagerComponent implements UserManager {

  private AddressManager addressManager;

  private QuerydslSupport querydslSupport;

  private TransactionHelper transactionHelper;

  private InternalValidatorManagerComponent validatorManager;

  private UserDTO convertUserDTO(final ListSubQuery<Long> sqAddressIds, final Tuple userTuple) {
    QUser qUser = QUser.user;
    UserDTO user = new UserDTO()
        .emailAddress(userTuple.get(qUser.emailAddress))
        .userId(userTuple.get(qUser.userId))
        .userName(userTuple.get(qUser.userName));
    List<Long> addressIds = userTuple.get(sqAddressIds);
    List<AddressDTO> addresses = new ArrayList<>();
    for (Long addressId : addressIds) {
      AddressDTO address = addressManager.getAddress(addressId);
      addresses.add(address);
    }
    user = user.addresses(addresses);
    return user;
  }

  @Override
  public long createUser(final CreateUserDTO user) {
    validatorManager.validateEmailAddress(user.emailAddress);
    validatorManager.validateUserName(user.userName);
    return transactionHelper.required(() -> {
      Long userId = querydslSupport.execute((connection, configuration) -> {
        QUser qUser = QUser.user;
        return new SQLInsertClause(connection, configuration, qUser)
            .set(qUser.emailAddress, user.emailAddress)
            .set(qUser.userName, user.userName)
            .executeWithKey(qUser.userId);
      });

      addressManager.createAddress(userId, user.address);

      return userId;
    });
  }

  @Override
  public UserDTO deleteUser(final long userId) {
    return transactionHelper.required(() -> {
      UserDTO user = getUser(userId);
      for (AddressDTO address : user.addresses) {
        addressManager.deleteAddress(address.addressId);
      }

      querydslSupport.execute((connection, configuration) -> {
        QUser qUser = QUser.user;
        return new SQLDeleteClause(connection, configuration, qUser)
            .where(qUser.userId.eq(userId))
            .execute();
      });
      return user;
    });
  }

  public AddressManager getAddressManager() {
    return addressManager;
  }

  public InternalValidatorManagerComponent getInternalValidatorManagerComponent() {
    return validatorManager;
  }

  public QuerydslSupport getQuerydslSupport() {
    return querydslSupport;
  }

  public TransactionHelper getTransactionHelper() {
    return transactionHelper;
  }

  @Override
  public UserDTO getUser(final long userId) {
    QAddress qAddress = QAddress.address;
    QUser qUser = QUser.user;
    ListSubQuery<Long> sqAddressIds = new SQLSubQuery()
        .from(qAddress)
        .where(qAddress.userId.eq(userId))
        .list(qAddress.addressId);
    Tuple userTuple = querydslSupport.execute((connection, configuration) -> {
      return new SQLQuery(connection, configuration)
          .from(qUser)
          .where(qUser.userId.eq(userId))
          .uniqueResult(qUser.userId,
              qUser.emailAddress,
              qUser.userName,
              sqAddressIds);
    });

    return convertUserDTO(sqAddressIds, userTuple);
  }

  @ServiceRef(defaultValue = "") // when save automatic search and bind service
  public void setAddressManager(final AddressManager addressManager) {
    this.addressManager = addressManager;
  }

  @ServiceRef(defaultValue = "")
  public void setInternalValidatorManagerComponent(
      final InternalValidatorManagerComponent internalValidatorManagerComponent) {
    validatorManager = internalValidatorManagerComponent;
  }

  @ServiceRef // not bind automatic
  public void setQuerydslSupport(final QuerydslSupport querydslSupport) {
    this.querydslSupport = querydslSupport;
  }

  @ServiceRef(defaultValue = "")
  public void setTransactionHelper(final TransactionHelper transactionHelper) {
    this.transactionHelper = transactionHelper;
  }

  @Override
  public void updateEmailAddress(final long userId, final String emailAdress) {
    validatorManager.validateEmailAddress(emailAdress);
    QUser qUser = QUser.user;
    updateStringField(qUser.userName, userId, emailAdress);
  }

  private void updateStringField(final StringPath column, final long userId,
      final String value) {
    querydslSupport.execute((connection, configuration) -> {
      QUser qUser = QUser.user;
      return new SQLUpdateClause(connection, configuration, qUser)
          .where(qUser.userId.eq(userId))
          .set(column, value)
          .execute();
    });
  }

  @Override
  public void updateUserName(final long userId, final String userName) {
    validatorManager.validateUserName(userName);
    QUser qUser = QUser.user;
    updateStringField(qUser.userName, userId, userName);
  }

}
