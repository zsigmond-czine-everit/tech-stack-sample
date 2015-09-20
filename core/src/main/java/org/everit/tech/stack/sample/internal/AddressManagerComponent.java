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

import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.ConfigurationPolicy;
import org.everit.osgi.ecm.annotation.Service;
import org.everit.osgi.ecm.annotation.ServiceRef;
import org.everit.osgi.ecm.extender.ECMExtenderConstants;
import org.everit.osgi.querydsl.support.QuerydslSupport;
import org.everit.osgi.transaction.helper.api.TransactionHelper;
import org.everit.tech.stack.sample.AddressManager;
import org.everit.tech.stack.sample.dto.AddressDTO;
import org.everit.tech.stack.sample.dto.CreateAddressDTO;
import org.everit.tech.stack.sample.schema.qdsl.QAddress;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Projections;
import com.mysema.query.types.path.StringPath;

import aQute.bnd.annotation.headers.ProvideCapability;

/**
 * Implementation of {@link AddressManager}.
 */
@Component(configurationPolicy = ConfigurationPolicy.REQUIRE)
@ProvideCapability(ns = ECMExtenderConstants.CAPABILITY_NS_COMPONENT,
    value = ECMExtenderConstants.CAPABILITY_ATTR_CLASS + "=${@class}")
@Service
public class AddressManagerComponent implements AddressManager {

  private QuerydslSupport querydslSupport;

  private TransactionHelper transactionHelper;

  private InternalValidatorManagerComponent validatorManager;

  @Override
  public long createAddress(final long userId, final CreateAddressDTO address) {
    validatorManager.validateCity(address.city);
    validatorManager.validateCountry(address.country);
    validatorManager.validateStreet(address.street);

    return transactionHelper.required(() -> {
      return querydslSupport.execute((connection, configuration) -> {
        QAddress qAddress = QAddress.address;
        return new SQLInsertClause(connection, configuration, qAddress)
            .set(qAddress.userId, userId)
            .set(qAddress.city, address.city)
            .set(qAddress.country, address.country)
            .set(qAddress.street, address.street)
            .executeWithKey(qAddress.addressId);
      });
    });
  }

  @Override
  public AddressDTO deleteAddress(final long addressId) {
    AddressDTO address = getAddress(addressId);
    querydslSupport.execute((connection, configuration) -> {
      QAddress qAddress = QAddress.address;
      return new SQLDeleteClause(connection, configuration, qAddress)
          .where(qAddress.addressId.eq(addressId))
          .execute();
    });
    return address;
  }

  @Override
  public AddressDTO getAddress(final long addressId) {
    return querydslSupport.execute((connection, configuration) -> {
      QAddress qAddress = QAddress.address;
      return new SQLQuery(connection, configuration)
          .from(qAddress)
          .where(qAddress.addressId.eq(addressId))
          .uniqueResult(Projections.constructor(AddressDTO.class,
              qAddress.addressId,
              qAddress.city,
              qAddress.country,
              qAddress.street,
              qAddress.userId));
    });
  }

  @ServiceRef
  public void setInternalValidatorManagerComponent(
      final InternalValidatorManagerComponent internalValidatorManagerComponent) {
    validatorManager = internalValidatorManagerComponent;
  }

  @ServiceRef
  public void setQuerydslSupport(final QuerydslSupport querydslSupport) {
    this.querydslSupport = querydslSupport;
  }

  @ServiceRef
  public void setTransactionHelper(final TransactionHelper transactionHelper) {
    this.transactionHelper = transactionHelper;
  }

  @Override
  public void updateCity(final long addressId, final String city) {
    validatorManager.validateCity(city);
    QAddress qAddress = QAddress.address;
    updateStringField(qAddress.city, addressId, city);
  }

  @Override
  public void updateCountry(final long addressId, final String country) {
    validatorManager.validateCountry(country);
    QAddress qAddress = QAddress.address;
    updateStringField(qAddress.country, addressId, country);
  }

  @Override
  public void updateStreet(final long addressId, final String street) {
    validatorManager.validateStreet(street);
    QAddress qAddress = QAddress.address;
    updateStringField(qAddress.street, addressId, street);
  }

  private void updateStringField(final StringPath column, final long addressId,
      final String value) {
    querydslSupport.execute((connection, configuration) -> {
      QAddress qAddress = QAddress.address;
      return new SQLUpdateClause(connection, configuration, qAddress)
          .where(qAddress.addressId.eq(addressId))
          .set(column, value)
          .execute();
    });
  }

}
