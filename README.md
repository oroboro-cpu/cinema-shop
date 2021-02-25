**CINEMA SHOP**

This project represents imitation of online cinema shop using N-tier architecture with DB layer, DAO layer, Service layer, Controllers layer and View layer.
Project was developed according to SOLID principles with authorization and authentication.

**UML DIAGRAM**

UML diagram that describes the relationship between the entities.

![img.png](img.png)

**CLIENT OPPORTUNITY**

Client can have multiple roles and different options depends on the role.

Without any role:

● Registration

● Authorization

● View a list of available movies

● View the list of cinema halls

● Find session by date

User role:

● View a list of available movies

● View the list of cinema halls

● View order list

● Find session by date

● Add sessions to shopping cart

● Make an order

● logout

Admin role:


● View / add movie

● View / add cinema hall

● Add movie session

● Find session by date

● Find user by email

● logout

**TECHNOLOGIES USED**

● backend: Java, Spring Core/MVC/Security, Hibernate, Jackson, Tomcat

● database: MySQL
tools: lombok, log4j

**START THE PROJECT**

To start the project you need:
1. Download and install the JDK
2. Download and install servlet container (for example Apache Tomcat)
3. Download and install MySQL Server
4. Setup new connection in the file **db.properties** in the resources' directory with

   ● user: "your username"

   ● password: "your password"

   ● url: jdbc:mysql://"your host name":"your port"/"your name db"?useUnicode=true&serverTimezone=UTC
   
5. Run a project