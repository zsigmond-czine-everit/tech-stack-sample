# tech-stack-sample
# IN PROGRESS

# 01-schema

Define database schema.

Create two table that use web application when is complete.

## Tables

### tss_user
Contains basic user information.

#### Columns
* user_id: the ID that identify user.
* user_name: the short name of user. 
* email_address: the email address of the user.

#### Restrictions
* The user_id is primary key with auto increment.
* The maximum length of user_name is 10 characters. 
* The maximum length of email_address is 100 characters.
* All fields are required.

### tss_address
Contains user address information.

#### Columns
* address_id: the ID that identify address.
* user_id: the ID of the user.
* city: the name of the city where user live. 
* country: the country where user live.
* street: the street name where user live.

#### Restrictions
* The address_id is primary key with auto increment.
* The maximum length of city, country and street are 100 characters. 
* Required fields are address\_id, user\_id and city.
* The user\_id is foreign key. 

## Define schema
We define schema with [Liquibase][1]. It's simple to use and support many database.

### Liqubiase in brief
To define schema with Liqubase must be create a [change log file][2]. Change log file contains [changeSet][3]s, [include][4]s, [preConditions][5] and [property][6]. The Liquibase support many format to create change log file. At now we use [XML format][7].

#### ChangeSet
Contains commands. General use:
```xml
<changeSet id="v001" author="everit">
  <!-- change commands -->
</changeSet>
```
* id: general the id is equal the version. 
* author: the creator of the change. 

#### Change Commands
The commands are similar to SQL commands. See in Liquibase [documentation][8].
##### Create table [see][9]
```xml
<createTable tableName="tss_user">
  <column name="user_id" type="bigint" autoIncrement="true">
    <constraints primaryKey="true" nullable="false" />
  </column>
  <!-- other columns -->
</createTable>
```
##### Foreign key [see][10]
```xml
<addForeignKeyConstraint constraintName="fk\_address\_user" baseTableName="tss\_user"
  baseColumnNames="user\_id" referencedTableName="tss\_address" referencedColumnNames="user\_id" />
```

## Generate schema
Simple to generate schema. Only add [lqmg-maven-plugin][11], [build-helper-maven-plugin][12] and add Provide-Capability in maven-bundle-plugin. The generatation is use [querydsl][13].

### lqmg-maven-plugin
Generate classes to src/main/generated/java/. The generator project is [osgi-lqmg][14]

```xml
<plugin>
  <groupId>org.everit.osgi.dev</groupId>
  <artifactId>lqmg-maven-plugin</artifactId>
  <version>3.0.1</version>
  <configuration>
    <defaultSchema>org.everit.tech.stack.sample.schema</defaultSchema>
    <capability>org.everit.tech.stack.sample.schema</capability>
    <packages>org.everit.tech.stack.sample.schema.qdsl</packages>
  </configuration>
</plugin>
```
* capability: The expression of the schema that should be the starting point of the generation.
* defaultSchema: Default schema that appears in the generated Metadata classes.
* packages: Comma separated list of packages that should be generated.

### build-helper-maven-plugin
Add source directory to project.

```xml
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>build-helper-maven-plugin</artifactId>
  <version>1.8</version>
  <executions>
    <execution>
      <phase>generate-sources</phase>
      <goals>
        <goal>add-source</goal>
      </goals>
      <configuration>
        <sources>
          <source>src/main/generated/java/</source>
        </sources>
      </configuration>
    </execution>
  </executions>
</plugin>
```

### Provide-Capability
Provide schema to other bundle.
```xml
<Provide-Capability>
  liquibase.schema;name=org.everit.tech.stack.sample.schema;resource=/META-INF/liquibase/tss.changelog-master.xml;lqmg.config.resource=/META-INF/liquibase/tss.lqmg.xml
</Provide-Capability>
```
The _liquibase.schema_ is required the generator search this capability. 
The _name_ is the unique name (we add this name in lqmg-maven-plugin/capability).
The _resource_ where to find change log file.
The _lqmg.config.resource_ the LQMG configuration file. Example how to call generated classes. See more details in [lqmg-3.0.0.xsd][15]

### Generate command
Generate schema to src/main/generated/java.

```
mvn bundle:bundle lqmg:generate
```

## After generate
After generate we has some problem. 

First problem is not find querydsl dependency and not build module (missing import). Add missing querydsl-sql dependency.
```xml
<dependency>
  <groupId>com.mysema.querydsl</groupId>
  <artifactId>querydsl-sql</artifactId>
  <version>3.6.7</version>
</dependency>
```

Second problem is the generated code contains some findbugs bugs. Solution is exclude bugs, but **only the schema module**.
```xml
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>findbugs-maven-plugin</artifactId>
  <configuration>
    <excludeFilterFile>tss-findbugs-exclude-filter.xml</excludeFilterFile>
  </configuration>
</plugin>
```
The tss-findbugs-exclude-filter.xml file contains the excluded bugs. At now exclude two: 
* SE\_BAD\_FIELD and 
* URF\_UNREAD\_PUBLIC\_OR\_PROTECTED\_FIELD.

## Build
```
mvn clean bundle:install
```

[1]: http://www.liquibase.org
[2]: http://www.liquibase.org/documentation/databasechangelog.html
[3]: http://www.liquibase.org/documentation/changeset.html
[4]: http://www.liquibase.org/documentation/include.html
[5]: http://www.liquibase.org/documentation/preconditions.html
[6]: http://www.liquibase.org/documentation/changelog_parameters.html
[7]: http://www.liquibase.org/documentation/xml_format.html
[8]: http://www.liquibase.org/documentation/changes/index.html
[9]: http://www.liquibase.org/documentation/changes/create_table.html
[10]: http://www.liquibase.org/documentation/changes/add_foreign_key_constraint.html
[11]: https://github.com/everit-org/lqmg-maven-plugin
[12]: http://www.mojohaus.org/build-helper-maven-plugin/index.html
[13]: http://www.querydsl.com/
[14]: https://github.com/everit-org/osgi-lqmg
[15]: https://github.com/everit-org/osgi-lqmg/blob/master/src/main/resources/META-INF/lqmg-3.0.0.xsd