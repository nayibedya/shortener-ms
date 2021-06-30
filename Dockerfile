FROM  amazoncorretto:11-alpine-jdk
WORKDIR /app
COPY ./jarFile/shortener-0.0.1-SNAPSHOT.jar ./
ENTRYPOINT ["java","-jar","shortener-0.0.1-SNAPSHOT.jar"]