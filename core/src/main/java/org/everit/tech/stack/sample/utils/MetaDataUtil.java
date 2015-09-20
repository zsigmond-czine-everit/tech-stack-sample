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
package org.everit.tech.stack.sample.utils;

import org.everit.tech.stack.sample.schema.qdsl.QAddress;
import org.everit.tech.stack.sample.schema.qdsl.QUser;

import com.mysema.query.sql.RelationalPathBase;
import com.mysema.query.types.Path;

/**
 * Utility class that provides the meta data of the schema.
 */
public final class MetaDataUtil {

  public static int getCityLength() {
    QAddress qAddress = QAddress.address;
    return MetaDataUtil.getSize(qAddress, qAddress.city);
  }

  public static int getCountryLength() {
    QAddress qAddress = QAddress.address;
    return MetaDataUtil.getSize(qAddress, qAddress.country);
  }

  public static int getEmailAddressLength() {
    QUser qUser = QUser.user;
    return MetaDataUtil.getSize(qUser, qUser.emailAddress);
  }

  private static int getSize(final RelationalPathBase<?> table, final Path<?> column) {
    return table.getMetadata(column).getSize();
  }

  public static int getStreetLength() {
    QAddress qAddress = QAddress.address;
    return MetaDataUtil.getSize(qAddress, qAddress.street);
  }

  public static int getUseNameLength() {
    QUser qUser = QUser.user;
    return MetaDataUtil.getSize(qUser, qUser.userName);
  }

  private MetaDataUtil() {
  }
}
