FROM adoptopenjdk/openjdk11:alpine-slim
ENV spring_profiles_active=prod
COPY target/ticketservice-0.0.1-SNAPSHOT.jar ticketservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "ticketservice-0.0.1-SNAPSHOT.jar"]