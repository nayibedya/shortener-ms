# shortener-micro-service
Micro-service for shortener application

#Dependencies
- Java 11
- h2 database
- maven

To Start the Application Run ShortenerApplication.java

##API Lists

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

 
