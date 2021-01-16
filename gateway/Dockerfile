FROM openjdk:8-jdk-alpine
EXPOSE 9080
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar","--debug" ,"-Dspring.profiles.active=zone2"]