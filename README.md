# Mail Sender Project

A mail project  that retrieves the information of the logged-in user and sends an email to this user using the specified parameters in the request.
## Features

- Sending mails
    * Basic mails
    * Mails with attachments
    * HTML mails
- JWT based authentication and authorization.
- RESTful APIs with Spring Boot Web and Security.
- Database integration with Spring Data JPA and PostgreSQL.
- ModelMapper for object mapping.

## Technologies
* Java 17
* Spring Boot 3.0.5
    * Mail
    * JPA Repository
    * Lombok
    * Model Mapper
* Thymeleaf
* JWT
* PostgreSQL
* Open API

## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 14+
* Maven 3+

To build and run the project, follow these steps:

* Clone the repository: `https://github.com/azizCan10/mail-sender.git`
* Navigate to the project directory
* Open a db in PostgreSQL called mail
* in application.properties file, set your own values

* Build the project: `mvn clean install`
* Run the project: `mvn spring-boot:run`

-> The application will be available at http://localhost:8080

-> Swagger will be available at http://localhost:8080/swagger-ui/index.html

---

# Screenshots

### HTML EMAIL
<img width="629" alt="Ekran Resmi 2024-04-12 01 23 29" src="https://github.com/azizCan10/mail-sender/assets/61064869/f0d97a7d-5d2c-49ed-a26d-334ff39b71b9">

