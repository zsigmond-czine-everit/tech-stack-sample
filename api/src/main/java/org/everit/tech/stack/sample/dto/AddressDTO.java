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
package org.everit.tech.stack.sample.dto;

/**
 * DTO class for address.
 */
public class AddressDTO {

  /**
   * The id of the address.
   */
  public long addressId;

  /**
   * The city name.
   */
  public String city;

  /**
   * The country name.
   */
  public String country;

  /**
   * The street name.
   */
  public String street;

  /**
   * The id of the user to who the address belongs.
   */
  public long userId;

  /**
   * Add id of the address to address.
   *
   * @param addressId
   *          the id of the address.
   * @return self object.
   */
  public AddressDTO addressId(final long addressId) {
    this.addressId = addressId;
    return this;
  }

  /**
   * Add city name to address.
   *
   * @param city
   *          the city name.
   * @return self object.
   */
  public AddressDTO city(final String city) {
    this.city = city;
    return this;
  }

  /**
   * Add country name to address.
   *
   * @param country
   *          the country name.
   * @return self object.
   */
  public AddressDTO country(final String country) {
    this.country = country;
    return this;
  }

  /**
   * Add street name to address.
   *
   * @param street
   *          the street name.
   * @return self object.
   */
  public AddressDTO street(final String street) {
    this.street = street;
    return this;
  }

  /**
   * Add user id to address.
   *
   * @param userId
   *          the id of the user to who the address belongs.
   * @return self object.
   */
  public AddressDTO userId(final long userId) {
    this.userId = userId;
    return this;
  }
}
