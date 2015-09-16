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
import org.everit.tech.stack.sample.UserManager;
import org.everit.tech.stack.sample.dto.CreateUserDTO;
import org.everit.tech.stack.sample.dto.UserDTO;

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

  private InternalValidatorManagerComponent internalValidatorManagerComponent;

  private QuerydslSupport querydslSupport;

  private TransactionHelper transactionHelper;

  @Override
  public long createUser(final CreateUserDTO user) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public UserDTO deleteUser(final long userId) {
    // TODO Auto-generated method stub
    return null;
  }

  public AddressManager getAddressManager() {
    return addressManager;
  }

  public InternalValidatorManagerComponent getInternalValidatorManagerComponent() {
    return internalValidatorManagerComponent;
  }

  public QuerydslSupport getQuerydslSupport() {
    return querydslSupport;
  }

  public TransactionHelper getTransactionHelper() {
    return transactionHelper;
  }

  @Override
  public UserDTO getUser(final long userId) {
    // TODO Auto-generated method stub
    return null;
  }

  @ServiceRef(defaultValue = "") // when save automatic search and bind service
  public void setAddressManager(final AddressManager addressManager) {
    this.addressManager = addressManager;
  }

  @ServiceRef(defaultValue = "")
  public void setInternalValidatorManagerComponent(
      final InternalValidatorManagerComponent internalValidatorManagerComponent) {
    this.internalValidatorManagerComponent = internalValidatorManagerComponent;
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
    // TODO Auto-generated method stub

  }

  @Override
  public void updateUserName(final long userId, final String userName) {
    // TODO Auto-generated method stub

  }

}
