# Rest Services

:white_check_mark: Compulsory - all bullets

:white_check_mark: Optional - all bullets

:white_check_mark: Bonus - 2/3 bullets

# Tasks regarding Compulsory part :star:

:heavy_check_mark: CRUD operations on players

 - Entities: **src/main/java/com/rest/entities**
 - DTO: **src/main/java/com/rest/dto**
 - Repositories: **src/main/java/com/rest/repositories**
 - Services: **src/main/java/com/rest/services**
 - Controllers: **src/main/java/com/rest/controllers**

:heavy_check_mark: -   Test your services using the browser and/or  [Postman](https://www.postman.com/): **postman-requests**

# Tasks regarding Optional part :star: :star:

:heavy_check_mark: Create REST services for inserting and reading games
 - Entities: **src/main/java/com/rest/entities**
 - DTO: **src/main/java/com/rest/dto**
 - Repositories: **src/main/java/com/rest/repositories**
 - Services: **src/main/java/com/rest/services**
 - Controllers: **src/main/java/com/rest/controllers**

:heavy_check_mark: Integrate the services into your previous project, invoking them using the support offered by Spring Boot

 - **src/main/java/com/gomoku/callservices**

:heavy_check_mark: Handle the exceptions using a **_RestControllerAdvice_**

 - **src/main/java/com/rest/exceptions**

:heavy_check_mark: Secure the communication using the **HTTPS** protocol

 - Generate a self-signed certificate using the command inside **src/main/resources/certificate.txt**
 - The result is a SSL certificate: **src/main/resources/keystore/rest.p12**

# Tasks regarding Bonus part :star: :star: :star:

:x: Expose an AI engine for the game you have created as a REST service

 - To do

:heavy_check_mark: Document your services using [Swagger](https://swagger.io/) or a similar tool

 - Configuration: **src/main/java/com/rest/swagger/SpringFoxConfig**
 - Also, each model and each controller has his own documentation (I used annotations like **@ApiOperation**, **@ApiParam** or **@ApiModelProperty**)

:heavy_check_mark: Secure your services with [JSON Web Tokens](https://jwt.io/)

 - **src/main/java/com/rest/security**