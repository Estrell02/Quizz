FROM ubuntu:latest
LABEL authors="estrella"

# Build
FROM maven:3.8.6-amazoncorretto-17 AS myapp-build
ENV MYAPP_HOME /opt/myapp
WORKDIR $MYAPP_HOME
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

# Run
FROM amazoncorretto:17
ENV MYAPP_HOME /opt/myapp
WORKDIR $MYAPP_HOME
COPY --from=myapp-build $MYAPP_HOME/target/quiz_platform-0.0.1-SNAPSHOT.jar $MYAPP_HOME/myapp.jar
EXPOSE 8080
ENTRYPOINT java -jar myapp.jar


