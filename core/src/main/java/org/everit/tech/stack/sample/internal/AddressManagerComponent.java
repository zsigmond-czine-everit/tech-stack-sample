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

import aQute.bnd.annotation.headers.ProvideCapability;

/**
 * Implementation of {@link AddressManager}.
 */
@Component(configurationPolicy = ConfigurationPolicy.REQUIRE)
@ProvideCapability(ns = ECMExtenderConstants.CAPABILITY_NS_COMPONENT,
    value = ECMExtenderConstants.CAPABILITY_ATTR_CLASS + "=${@class}")
@Service
public class AddressManagerComponent implements AddressManager {

  private InternalValidatorManagerComponent internalValidatorManagerComponent;

  private QuerydslSupport querydslSupport;

  private TransactionHelper transactionHelper;

  @Override
  public long createAddress(final long userId, final CreateAddressDTO address) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public AddressDTO deleteAddress(final long addressId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public AddressDTO getAddress(final long addressId) {
    // TODO Auto-generated method stub
    return null;
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

  @ServiceRef
  public void setInternalValidatorManagerComponent(
      final InternalValidatorManagerComponent internalValidatorManagerComponent) {
    this.internalValidatorManagerComponent = internalValidatorManagerComponent;
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
    // TODO Auto-generated method stub

  }

  @Override
  public void updateCountry(final long addressId, final String country) {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateStreet(final long addressId, final String street) {
    // TODO Auto-generated method stub

  }

}
