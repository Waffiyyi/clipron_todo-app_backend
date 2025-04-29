FROM maven:3.8.6-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/clipronTodoApp.jar clipronTodoApp.jar

EXPOSE 8011

ENTRYPOINT ["java", "-jar", "clipronTodoApp.jar"]
