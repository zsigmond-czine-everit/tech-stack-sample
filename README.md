# tech-stack-sample
# IN PROGRESS

# 02-database-config

Previous branch create (01-schema) a database schema. At now configure some components in tests module that create database to tests.

## Schema import
We want use the a _org.everit.tech.stack.sample.schema_ schema so add to Require-Capability. It can be optional, but sometimes help to find problems (e.g.: missing dependency).

```xml
<Require-Capability>
  liquibase.schema;filter:="(name=org.everit.tech.stack.sample.schema)"
</Require-Capability>
```

Add dependency too.

```xml
<dependency>
  <groupId>org.everit.tech.stack.sample</groupId>
  <artifactId>org.everit.tech.stack.sample.schema</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## Required dependencies
See more details from dependencies:
* [osgi-liquibase-datasource][1]
* [osgi-liquibase-bundle][2]
* [osgi-jdbc-dsf][3]
* [commons-dbcp-component][4]
* [h2][5]
* [jtds-component][6]
* JDBC Service API 1.0 for OSGi Enterprise [ow2-jdbc-service-1.0-spec][7].

```xml
<!-- Database related modules -->
<dependency>
  <groupId>org.everit.osgi</groupId>
  <artifactId>org.everit.osgi.liquibase.datasource</artifactId>
  <version>1.0.1</version>
</dependency>
<dependency>
  <groupId>org.everit.osgi</groupId>
  <artifactId>org.everit.osgi.liquibase.bundle</artifactId>
  <version>3.1.1-v20140507</version>
</dependency>
<dependency>
  <groupId>org.everit.osgi</groupId>
  <artifactId>org.everit.osgi.jdbc.dsf</artifactId>
  <version>2.0.0</version>
</dependency>
<dependency>
  <groupId>org.everit.osgi</groupId>
  <artifactId>org.everit.osgi.jdbc.commons.dbcp</artifactId>
  <version>2.0.1</version>
</dependency>
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <version>1.4.189</version>
</dependency>
<dependency>
  <groupId>org.everit.osgi</groupId>
  <artifactId>org.everit.osgi.jtds.component</artifactId>
  <version>1.0.0</version>
</dependency>
<dependency>
  <groupId>org.ow2.spec.osgi</groupId>
  <artifactId>ow2-jdbc-service-1.0-spec</artifactId>
  <version>1.0.13</version>
</dependency>
```

## Build
At now build 'tests' module.

```
mvn clean bundle:install
```

## System console configuration
Now must be configure components. We configure components in https://localhost:4848/system/console/configMgr after we start server.

Navigate to bin folder in tests module.
```
cd tests/target/eosgi-dist/equinoxtest/bin
``` 

Run server.
```
./runConsole.sh or runConsole.bat
```

At now run server we configure only three components. The components configure minimal (has many attribute, read descriptions).

### Everit XADataSource
In XADataSource component configure JDBC connection so add JDBC driver class, JDBC URL, user name, password and other options.
We use H2 database so configure is very simple. Only fill two attribute.
We create many XADataSource if has two database we create two XADataSource.

* DataSourceFactory OSGi service filter: the JDBC driver class name eq.: (osgi.jdbc.driver.class=org.h2.Driver)
* Jdbc URL: the JDBC driver URL eq.: jdbc:h2:mem:test

### Everit Commons DBCP ManagedDataSource
After created XADataSource we create a ManagedDataSource. The XADataSource will be managed and pooled.
It has many configuration options but we configure one.

* XADataSource service filter: copy XADataSource service pid and and filter e.g.: (service.pid=org.everit.osgi.jdbc.dsf.XADataSource.20720637-00fd-4df7-a57e-e8ffffda2520).

### DataSource (Liquibase) (Everit)
If has ManagedDataSource we create datasource to Liquibase which create database. It's not heavy to configure. Add schema expression and the ManagedDataSource.

* Schema expression: the schema expression which we provide from the bundle e.q.: org.everit.tech.stack.sample.schema
* Embedd DataSource: the datasource which belongs to the database e.q.: (service.pid=org.everit.osgi.jdbc.commons.dbcp.ManagedDataSource.df9cc6ea-7278-450c-82f3-60bd27f5b8f0)

If we configure components well, we see this rows in log:
* liquibase: org.everit.tech.stack.sample.schema: v001::zsigmond-czine: Table tss_user created
* liquibase: org.everit.tech.stack.sample.schema: v001::zsigmond-czine: Table tss_address created
* liquibase: org.everit.tech.stack.sample.schema: v001::zsigmond-czine: Foreign key contraint added to tss\_user (user\_id)
* ...

[1]: https://github.com/everit-org/osgi-liquibase-datasource
[2]: https://github.com/everit-org/osgi-liquibase-bundle
[3]: https://github.com/everit-org/osgi-jdbc-dsf
[4]: https://github.com/everit-org/commons-dbcp-component
[5]: http://www.h2database.com/html/main.html
[6]: https://github.com/everit-org/jtds-component
[7]: http://search.maven.org/#artifactdetails|org.ow2.spec.osgi|ow2-jdbc-service-1.0-spec|1.0.13|bundle