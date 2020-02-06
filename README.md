## person-backend-application
SpringBoot Restful Web Service Person Application CRUD(Create, Read, Update and Delete) using SpringBoot, Rest, Swagger Documentation, H2 database and an embedded Apache tomcat server.

## API Documentation
The Swagger API Documentation for this application can be accessed via https://www.stagingapp.live/person/swagger-ui.html

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


