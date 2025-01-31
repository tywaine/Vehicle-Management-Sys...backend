FROM openjdk:23-jdk-slim

COPY target/vehiclemanagement-0.0.1-SNAPSHOT.jar /vehiclemanagement.jar

EXPOSE 8081

CMD ["java", "-jar", "/vehiclemanagement.jar"]