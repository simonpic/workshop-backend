FROM adoptopenjdk/openjdk11:alpine-slim
ENV spring_profiles_active=prod
COPY target/api-0.0.1-SNAPSHOT.jar api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]