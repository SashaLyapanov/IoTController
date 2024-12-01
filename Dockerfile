FROM openjdk:17-jdk-slim-buster

WORKDIR /iotController

COPY /target/IoTController-0.0.1-SNAPSHOT.jar /iotController/IoTController.jar

ENTRYPOINT java -jar IoTController.jar