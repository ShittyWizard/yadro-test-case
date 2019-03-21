FROM openjdk:8-jdk-alpine
LABEL maintainer="anisimov.develop@gmail.com"

COPY server/target/server-1.0-SNAPSHOT.jar /usr/src/server.jar

EXPOSE 8080

WORKDIR /usr/src

CMD java -jar server.jar