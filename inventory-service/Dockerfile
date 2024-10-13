FROM openjdk:17-jdk-alpine
COPY target/inventory-service.jar inventory-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/inventory-service.jar"]