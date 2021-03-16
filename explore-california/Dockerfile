FROM openjdk:11-jdk-slim
WORKDIR /
ADD target/explore-california-0.0.2-SNAPSHOT.jar //
EXPOSE 8100
ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=docker", "/explore-california-0.0.2-SNAPSHOT.jar"]
