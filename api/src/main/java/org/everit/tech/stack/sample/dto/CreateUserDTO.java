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
 * DTO class for user to create.
 */
public class CreateUserDTO {

  /**
   * The information of address to create.
   */
  public CreateAddressDTO address;

  /**
   * The user's email address.
   */
  public String emailAddress;

  /**
   * The user's user name.
   */
  public String userName;

  /**
   * Add address to create.
   *
   * @param address
   *          address information to create.
   * @return self object.
   */
  public CreateUserDTO address(final CreateAddressDTO address) {
    this.address = address;
    return this;
  }

  /**
   * Add user's email address.
   *
   * @param emailAddress
   *          the user's email address.
   * @return self object.
   */
  public CreateUserDTO emailAddress(final String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  /**
   * Add user's user name.
   * 
   * @param userName
   *          the user's user name.
   * @return self object.
   */
  public CreateUserDTO userName(final String userName) {
    this.userName = userName;
    return this;
  }

}
