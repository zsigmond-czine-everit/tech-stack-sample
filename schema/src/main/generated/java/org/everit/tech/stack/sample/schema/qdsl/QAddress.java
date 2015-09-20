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
package org.everit.tech.stack.sample.schema.qdsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;




/**
 * QAddress is a Querydsl query type for QAddress
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAddress extends com.mysema.query.sql.RelationalPathBase<QAddress> {

    private static final long serialVersionUID = -180259815;

    public static final QAddress address = new QAddress("tss_address");

    public class PrimaryKeys {

        public final com.mysema.query.sql.PrimaryKey<QAddress> tssAddressPk = createPrimaryKey(addressId);

    }

    public class ForeignKeys {

        public final com.mysema.query.sql.ForeignKey<QUser> _addressUserFk = createInvForeignKey(userId, "user_id");

    }

    public final NumberPath<Long> addressId = createNumber("addressId", Long.class);

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final StringPath street = createString("street");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final PrimaryKeys pk = new PrimaryKeys();

    public final ForeignKeys fk = new ForeignKeys();

    public QAddress(String variable) {
        super(QAddress.class, forVariable(variable), "org.everit.tech.stack.sample.schema", "tss_address");
        addMetadata();
    }

    public QAddress(String variable, String schema, String table) {
        super(QAddress.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAddress(Path<? extends QAddress> path) {
        super(path.getType(), path.getMetadata(), "org.everit.tech.stack.sample.schema", "tss_address");
        addMetadata();
    }

    public QAddress(PathMetadata<?> metadata) {
        super(QAddress.class, metadata, "org.everit.tech.stack.sample.schema", "tss_address");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(addressId, ColumnMetadata.named("address_id").ofType(-5).withSize(19).notNull());
        addMetadata(city, ColumnMetadata.named("city").ofType(12).withSize(100).notNull());
        addMetadata(country, ColumnMetadata.named("country").ofType(12).withSize(100));
        addMetadata(street, ColumnMetadata.named("street").ofType(12).withSize(100));
        addMetadata(userId, ColumnMetadata.named("user_id").ofType(-5).withSize(19).notNull());
    }

}

