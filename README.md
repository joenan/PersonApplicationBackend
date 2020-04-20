## person-backend-application
SpringBoot Restful Web Service Person Application CRUD(Create, Read, Update and Delete) using SpringBoot, Rest, Swagger Documentation, H2 database and an embedded Apache tomcat server.

Note: Please note that the application.properties has a default profile configuration (spring.profiles.active=dev). This implies that the application-dev.properties file is the selected profile. you can however change it to prod (spring.profiles.active=prod). By doing so, the aplication-prod.properties file will be the default configuration.

## API Documentation
The Swagger API Documentation for this application can be accessed via https://www.stagingapp.live/person/swagger-ui.html
The front-end for this application can be accessed via http://www.stagingapp.live/person-front-end

## Build
To Build this application on the command Line, Run the command "mvn install"

## Run
To run this application on the command Line, Run the command "mvn spring-boot:run". The backend can be accessed via this context path on can be accessed via http://localhost:8080/person after  it has run.



## Deploy
To deploy this application in Tomcat Apache Server, copy the built application to tomcat/webapps directory. The app will be automatically deployed.

## Running Backènd Test
Run the command 'mvn test' on the terminal to run all test cases on the backend

## Running Individual Test
To run the PersonController Integration test, Run the command mvn -Dtest=PersonControllerIntegrationTest test

To run the PersonController Unit test, Run the command mvn -Dtest=PersonControllerTest test

To run the PersonRepository Unit test, Run the command mvn -Dtest=PersonRepositoryTest test

To run the PersonService Unit test, Run the command mvn -Dtest=PersonServiceTest test


