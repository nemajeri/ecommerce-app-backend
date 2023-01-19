# ecommerce-app-backend

This is a Spring Boot project for a ecommerce web application developed by me.

Before running in local environment or on a Docker container keep in mind there are two classes in Configuration file that act like scripts to populate a DB, there are: AppUserConfig and ProductConfig.

Also connection string isn`t provided so you should make one and connect to his/hers database when in local development. Docker image uses JDBC driver 9.4.1 to establish connection with MSSQL Server.

If you want to change the database, the docker images should be configured accordingly and rebuilt.

There are no unit tests done. 
