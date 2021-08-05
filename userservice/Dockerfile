FROM adoptopenjdk/openjdk11:alpine-slim
ENV spring_profiles_active=prod
COPY target/userservice-0.0.1-SNAPSHOT.jar userservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "userservice-0.0.1-SNAPSHOT.jar"]