# ecommerce-app-backend

This is a Spring Boot project for a ecommerce web application developed by me.

Before running in local environment or on a Docker container keep in mind there are two classes in Configuration file that act like scripts to populate a DB.

Also connection string isnt provided so you the user should make one and connect to his/hers database. Docker image uses JDBC driver 9.4.1 to establish connection with MSSQL Server.

If user wants to change the database, the docker image should be configured accordingly.
