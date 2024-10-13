FROM openjdk:17-jdk-alpine
COPY target/delivery-service.jar delivery-service.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/delivery-service.jar"]