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
 * DTO class for address to create.
 */
public class CreateAddressDTO {

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
   * Add city name to address.
   *
   * @param city
   *          the city name.
   * @return self object.
   */
  public CreateAddressDTO city(final String city) {
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
  public CreateAddressDTO country(final String country) {
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
  public CreateAddressDTO street(final String street) {
    this.street = street;
    return this;
  }

}
