FROM amazoncorretto:1.8-alpine-jdk
COPY target/lubricentro-0.0.1-SNAPSHOT.jar /app-v1.jar
ENTRYPOINT ["java", "-jar", "/app-v1.jar"]


