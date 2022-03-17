Challenge Backend - Java Spring Boot
-An Api rest built in JDK11 and Maven with Spring Boot, JWT, Lombok, JPA, Mysql, SendGrid and NetBeans. Swagger for documentation
-Runs in localhost:8080
-Some clarifications: 
	-Use the initialScript.sql file to set the db
	-The only user with role admin is: arturo55
	-Initial script User credentials:
		-email: arturo@mail.com; user: arturo55; password: password, role: ADMIN
		-email: mark@mail.com; user: mark22;  password: password, role: USER
	-Movies and Characters ARE NOT associated to eachother, you can do it by using the api's functionalities
	-Swagger documentation in: http://localhost:8080/swagger-ui/
	-Don't forguet to change your database user and password in application.properties :)
	-If you are reading this, and you want to run this app and be able to send email with SendGrid when a user is registered, write an email to me (Arturo, arturofrodriguez11@gmail.com) and I will be happy to give you the ApiKey to configure the application.properties file. You would just need to uncomment line 16 and put the right key (that I will provide).
Hope everything is clear, let me know any thoughts and comments.
Cheers
