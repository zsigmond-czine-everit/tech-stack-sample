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

import java.util.regex.Pattern;

import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.ConfigurationPolicy;
import org.everit.osgi.ecm.annotation.Service;
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.extender.ECMExtenderConstants;
import org.everit.tech.stack.sample.utils.MetaDataUtil;

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

  @StringAttribute(attributeId = PROP_EMAIL_ADDRESS_REGEX,
      defaultValue = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*"
          + "(\\.[A-Za-z]{2,})$")
  public void setEmailAddressRegex(final String emailAddressRegex) {
    this.emailAddressRegex = emailAddressRegex;
  }

  /**
   * Validate city.
   */
  public void validateCity(final String city) {
    if ((city == null) || city.isEmpty()) {
      throw new RuntimeException("city is required");
    }

    if (city.length() > MetaDataUtil.getCityLength()) {
      throw new RuntimeException("city is too length");
    }
  }

  /**
   * Validate country.
   */
  public void validateCountry(final String country) {
    if (country.length() > MetaDataUtil.getCountryLength()) {
      throw new RuntimeException("country is too length");
    }
  }

  /**
   * Validate email address.
   */
  public void validateEmailAddress(final String emailAddress) {
    if ((emailAddress == null) || emailAddress.isEmpty()) {
      throw new RuntimeException("city is required");
    }

    if (!Pattern.matches(emailAddressRegex, emailAddress)) {
      throw new RuntimeException("emailAddress is not valid");
    }

    if (emailAddress.length() > MetaDataUtil.getEmailAddressLength()) {
      throw new RuntimeException("city is too length");
    }
  }

  /**
   * Validate street.
   */
  public void validateStreet(final String street) {
    if (street.length() > MetaDataUtil.getStreetLength()) {
      throw new RuntimeException("street is too length");
    }
  }

  /**
   * Validiate user name.
   */
  public void validateUserName(final String userName) {
    if ((userName == null) || userName.isEmpty()) {
      throw new RuntimeException("userName is required");
    }

    if (userName.length() > MetaDataUtil.getUseNameLength()) {
      throw new RuntimeException("userName is too length");
    }
  }

}
