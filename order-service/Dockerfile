# # Use a Maven image to build the application
# FROM maven:3.8.5-openjdk-17 AS build
# WORKDIR /app
# COPY pom.xml .
# COPY src ./src
# RUN mvn clean package -DskipTests

# # Use a minimal JDK image to run the application
# FROM openjdk:17-jdk-alpine
# COPY --from=build /app/target/order-service.jar order-service.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","/order-service.jar"]

FROM openjdk:17-jdk-alpine
COPY target/order-service.jar order-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/order-service.jar"]