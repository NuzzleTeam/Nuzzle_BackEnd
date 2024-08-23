FROM openjdk:21
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY .env .env
ENTRYPOINT ["java", "-jar", "app.jar"]