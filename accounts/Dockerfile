FROM openjdk:17-jdk-slim
LABEL authors="Tiago Fernandes Ribeiro"

MAINTAINER tiagoribeiro.uservices

COPY target/accounts-v1.jar accounts-v1.jar

ENTRYPOINT ["java", "-jar", "accounts-v1.jar"]