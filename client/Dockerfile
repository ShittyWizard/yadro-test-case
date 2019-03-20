FROM openjdk:8-jdk-alpine
LABEL maintainer="anisimov.develop@gmail.com"

COPY target/client-1.0-SNAPSHOT.jar /usr/src/client.jar

WORKDIR /usr/src

CMD java -jar client.jar list -s localhost -p 8080