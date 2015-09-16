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

import org.everit.tech.stack.sample.dto.CreateUserDTO;
import org.everit.tech.stack.sample.dto.UserDTO;

/**
 * Responsible to manage user.
 */
public interface UserManager {

  /**
   * Create a new user in system.
   *
   * @param user
   *          the information of user.
   * @return the created user id.
   */
  long createUser(CreateUserDTO user);

  /**
   * Delete user from system.
   *
   * @param userId
   *          the id of the user.
   * @return the deleted user informations.
   */
  UserDTO deleteUser(long userId);

  /**
   * Gets user information.
   *
   * @param userId
   *          the id of the user.
   * @return the user informations.
   */
  UserDTO getUser(long userId);

  /**
   * Updating the user's email address.
   *
   * @param userId
   *          the id of the user.
   * @param newEmailAdress
   *          the new email address.
   */
  void updateEmailAddress(long userId, String newEmailAdress);

  /**
   * Updating the user's user name.
   *
   * @param userId
   *          the id of the user.
   * @param newUserName
   *          the new user name.
   */
  void updateUserName(long userId, String newUserName);
}
