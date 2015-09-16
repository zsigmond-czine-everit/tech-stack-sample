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
package org.everit.tech.stack.sample;

import org.everit.tech.stack.sample.dto.AddressDTO;
import org.everit.tech.stack.sample.dto.CreateAddressDTO;

/**
 * Responsible to manage address.
 */
public interface AddressManager {

  /**
   * Create new address to user.
   *
   * @param userId
   *          the is of the user.
   * @param address
   *          information of address.
   * @return the created address id.
   */
  long createAddress(long userId, CreateAddressDTO address);

  /**
   * Delete address from system.
   *
   * @param addressId
   *          the id of the address.
   * @return information of the deleted address.
   */
  AddressDTO deleteAddress(long addressId);

  /**
   * Gets address based on addressId.
   *
   * @param addressId
   *          the id of the address.
   * @return the address information.
   */
  AddressDTO getAddress(long addressId);

  /**
   * Updating the city's name.
   * 
   * @param addressId
   *          the id of address.
   * @param newCity
   *          the new city name.
   */
  void updateCity(long addressId, String newCity);

  /**
   * Updating the country's name.
   * 
   * @param addressId
   *          the id of the address.
   * @param newCountry
   *          the new country name.
   */
  void updateCountry(long addressId, String newCountry);

  /**
   * Updating the street's name.
   * 
   * @param addressId
   *          the id of the address.
   * @param newStreet
   *          the new street name.
   */
  void updateStreet(long addressId, String newStreet);
}
