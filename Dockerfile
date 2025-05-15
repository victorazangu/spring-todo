FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
# Download dependencies first to leverage Docker cache
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# Install netcat for healthcheck
RUN apk add --no-cache bash netcat-openbsd
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
