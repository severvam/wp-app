# Read Me First
As there was no requirements regarding responsible person who will create offers for customers, I created small table with necessary data. In real world application there will be some external system that handles users, their rights etc.

Requiremens:
* Java 11+

Gradle will be automatically fetched during first run.
Database is in memory.

Application tested on macOS and Ubuntu. On Windows also should be possible to run this app, but I don't have Windows machine to proof that.

# Getting Started

Two options how to run application.

1. Run using gradle:
```shell script
./gradlew bootRun
```

2. Build and run jar:

```shell script
./gradlew clean build && java -jar build/libs/worldpay-app-20.2.0.jar
```

Application is REST service, so no explicit frontend is created. For tests or available endpoint review you can use swagger-ui with contract description.\
Swagger address: `http://localhost:3000/swagger-ui.html`

### Technologies and frameworks

* Java 11+
* Gradle
* Spring Boot
* Swagger
* H2 database
* Liquibase
* Spock Framework
* Lombok
