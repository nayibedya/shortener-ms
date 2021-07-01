# shortener-micro-service
Micro-service for shortener application

# Dependencies
- Java 11
- h2 database
- maven

## RUN Steps

### Using Docker
- pre-requisite : Docker engine should be installed on the machine
- docker-compose up -> to start the application
- docker-compose down -> to stop the application
- application will be served at http://localhost:8085

### locally
- To Start the Application Run ShortenerApplication.java.
- Application will be server at port http://localhost:8085.
- To check if the application has started successfully check http://localhost:8085/actuator/health
- If the response is "UP" then application is started.
- To change the port number go to application.yml 



## API Lists

### /generate
This api take an URL as an input and returns shortened String url.

### /getAll
This api returns list of all url's.

### /{urlKey}
This redirects to the actual url and keeps the number of count
the url has been used.

### mvn clean install : to get the packaged jar
### mvn clean verify : to run the test suit

Packaged jar will be available in target folder under root directory.


