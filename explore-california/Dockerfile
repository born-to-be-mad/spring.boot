FROM openjdk:11-jdk-slim
WORKDIR /
ADD target/explore-california-0.0.1-SNAPSHOT.jar //
EXPOSE 8100
ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=mysql", "/explore-california-0.0.1-SNAPSHOT.jar"]

#COPY target/*.jar app.jar
#ENTRYPOINT [ "java", "-jar", "/app.jar"]
