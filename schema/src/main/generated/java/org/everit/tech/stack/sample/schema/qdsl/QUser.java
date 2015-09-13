package org.everit.tech.stack.sample.schema.qdsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;




/**
 * QUser is a Querydsl query type for QUser
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QUser extends com.mysema.query.sql.RelationalPathBase<QUser> {

    private static final long serialVersionUID = 1294962118;

    public static final QUser user = new QUser("tss_user");

    public class PrimaryKeys {

        public final com.mysema.query.sql.PrimaryKey<QUser> tssUserPk = createPrimaryKey(userId);

    }

    public class ForeignKeys {

        public final com.mysema.query.sql.ForeignKey<QAddress> addressUserFk = createForeignKey(userId, "user_id");

    }

    public final StringPath emailAddress = createString("emailAddress");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath userName = createString("userName");

    public final PrimaryKeys pk = new PrimaryKeys();

    public final ForeignKeys fk = new ForeignKeys();

    public QUser(String variable) {
        super(QUser.class, forVariable(variable), "org.everit.tech.stack.sample.schema", "tss_user");
        addMetadata();
    }

    public QUser(String variable, String schema, String table) {
        super(QUser.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QUser(Path<? extends QUser> path) {
        super(path.getType(), path.getMetadata(), "org.everit.tech.stack.sample.schema", "tss_user");
        addMetadata();
    }

    public QUser(PathMetadata<?> metadata) {
        super(QUser.class, metadata, "org.everit.tech.stack.sample.schema", "tss_user");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(emailAddress, ColumnMetadata.named("email_address").ofType(12).withSize(100).notNull());
        addMetadata(userId, ColumnMetadata.named("user_id").ofType(-5).withSize(19).notNull());
        addMetadata(userName, ColumnMetadata.named("user_name").ofType(12).withSize(10).notNull());
    }

}

