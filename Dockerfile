FROM maven:3.6.3-openjdk-15-slim AS builder
COPY pom.xml .
RUN mvn de.qaware.maven:go-offline-maven-plugin:1.2.1:resolve-dependencies
COPY . .
RUN mvn package -f pom.xml -U

FROM openjdk:15-jdk-alpine
COPY --from=builder /target/demo-1.0.0-SNAPSHOT.jar demo-1.0.0-SNAPSHOT.jar
COPY --from=builder /src/main/resources/static/sample.json /src/main/resources/static/sample.json
CMD java -jar demo-1.0.0-SNAPSHOT.jar