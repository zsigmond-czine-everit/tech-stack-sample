# tech-stack-sample
# IN PROGRESS

Shows how to create web application with EverIT technology stack.

The sample is very simple. We implement a simple user management (CRUD) and authenticate user to access a protected page.

We are moving step by step. One branch is one step that built on each other.

The first step create a project structure with found in master branch. The structure differs from the convention, but suitable to show technology stack. 

Modules:
* api: interfaces and classes (not separate public and internal API).
* core: reference implementation.
* schema: database schema
* web: the GUI.
* tests: integration tests.

In 'tests' module we find a minimal configuration that able to run integration tests.

(**DRAFT**)
## Sections 
* schema definition with Liquibase and schema generation - 01-schema
* database configuration in tests module (system console) - 02-database-config
* ECM create core components
* ECM configuration in tests module. (system console)
* create tests components and configure in system console
* web (TODO must be split) 

some key word
* templating
* partialresponse
* resource
* property manager
* form-authentication (concepts)
* audit
* i18n-props-xls-converter 