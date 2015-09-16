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
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.extender.ECMExtenderConstants;

import aQute.bnd.annotation.headers.ProvideCapability;

/**
 * Component that validate user and address information.
 */
@Component(configurationPolicy = ConfigurationPolicy.REQUIRE)
@ProvideCapability(ns = ECMExtenderConstants.CAPABILITY_NS_COMPONENT,
    value = ECMExtenderConstants.CAPABILITY_ATTR_CLASS + "=${@class}")
@Service(value = InternalValidatorManagerComponent.class)
public class InternalValidatorManagerComponent {

  public static final String PROP_EMAIL_ADDRESS_REGEX = "email.address.regex";

  private String emailAddressRegex;

  public String getEmailAddressRegex() {
    return emailAddressRegex;
  }

  @StringAttribute(attributeId = PROP_EMAIL_ADDRESS_REGEX,
      defaultValue = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*"
          + "(\\.[A-Za-z]{2,})$")
  public void setEmailAddressRegex(final String emailAddressRegex) {
    this.emailAddressRegex = emailAddressRegex;
  }

  public void validateCity(final String city) {
    // TODO Auto-generated method stub

  }

  public void validateCountry(final String country) {
    // TODO Auto-generated method stub

  }

  public void validateEmailAddress(final String emailAddress) {
    // TODO Auto-generated method stub

  }

  public void validateStreet(final String street) {
    // TODO Auto-generated method stub

  }

  public void validateUserName(final String userName) {
    // TODO Auto-generated method stub

  }

}
