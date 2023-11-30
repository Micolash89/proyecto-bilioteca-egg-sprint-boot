
FROM maven:3.8.4-openjdk-8 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

#FROM amazoncorretto:17-alpine

FROM openjdk:8-alpine
#FROM amazoncorretto:8-alpine-jdk

COPY target/libreria-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

 EXPOSE 8080