FROM  openjdk:11-jdk
WORKDIR /app
COPY ./target/shortener-0.0.1-SNAPSHOT.jar ./
ENTRYPOINT ["java","-jar","shortener-0.0.1-SNAPSHOT.jar"]