
#FROM openjdk:21-oracle
FROM amazoncorretto:21-alpine-jdk

LABEL description="Auth service"
LABEL author="VTI"

WORKDIR /application

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]