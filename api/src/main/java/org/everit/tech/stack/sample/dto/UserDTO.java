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

import java.util.List;

/**
 * DTO class for user.
 */
public class UserDTO {

  /**
   * List of user addresses.
   */
  public List<AddressDTO> addresses;

  /**
   * The user's email address.
   */
  public String emailAddress;

  /**
   * The id of the user.
   */
  public long userId;

  /**
   * The user's user name.
   */
  public String userName;

  /**
   * Add user's addresses.
   * 
   * @param addresses
   *          the list of addresses.
   * @return self object.
   */
  public UserDTO addresses(final List<AddressDTO> addresses) {
    this.addresses = addresses;
    return this;
  }

  /**
   * Add user's email address.
   * 
   * @param emailAddress
   *          the user's email address.
   * @return self object.
   */
  public UserDTO emailAddress(final String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  /**
   * Add user's identifier.
   * 
   * @param userId
   *          the id of the user.
   * @return self object.
   */
  public UserDTO userId(final long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Add user's user name.
   * 
   * @param userName
   *          the user's user name.
   * @return self object.
   */
  public UserDTO userName(final String userName) {
    this.userName = userName;
    return this;
  }

}
