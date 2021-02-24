In this project used N-tier architecture with DB layer, DAO layer, Service layer, Controllers layer and View layer.
Project was developed according to SOLID principles with authorization and authentication.

UML diagram that describes the relationship between the entities.

![img.png](img.png)
One user can have multiple roles.

No role:

● Registration

● Authorization

● View a list of available movies

● View the list of cinema halls

● Find session by date

User:

● View a list of available movies

● View the list of cinema halls

● View order list

● Find session by date

● Add sessions to shopping cart

● Make an order

● logout

Admin:


● View / add movie

● View / add cinema hall

● Add movie session

● Find session by date

● Find user by email

● logout

Technologies used

● backend: Java, Spring Core/MVC/Security, Hibernate, Jackson, Tomcat

● database: MySQL
tools: lombok, log4j

To start the project you need:
1. Download and install the JDK
2. Download and install servlet container (for example Apache Tomcat)
3. Download and install MySQL Server
4. Setup new connection with

   ● user: "your username"

   ● password: "your password"

   ● url: jdbc:mysql://"your host name":"your port"/"your name db"?useUnicode=true&serverTimezone=UTC
5. Run a project