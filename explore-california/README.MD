# Getting Started

## API
Swagger UI:
* http://localhost:8100/v2/api-docs
* http://localhost:8100/swagger-ui.html


## Start with Docker

### Start app
* `docker run --detach --name ec-mysql -p 6604:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=explorecali -e MYSQL_USER=cali_user -e MYSQL_PASSWORD=cali_pass -d mysql` start MySQl server in a container
* `mvn package -DskipTests` to build the project
* `docker build -t explorecali .` to build the image
* `docker run --name ec-app -p 8100:8100 --link ec-mysql:mysql -d explorecali` to run app as a container
* `docker logs ec-app` to check that app is running without any issues
* `http://localhost:8100/tours` to check the packages

##### Start MySql Container (downloads image if not found)
``
docker run --detach --name ec-mysql -p 6604:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=explorecali -e MYSQL_USER=cali_user -e MYSQL_PASSWORD=cali_pass -d mysql
``

##### view all images
``
docker images
``

##### view all containers (running or not)
``
docker ps -a
``
##### Interact with Database (link to ec-mysql container) with mysql client
``
docker run -it --link ec-mysql:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
``

##### Connect to the database instance:
* you can connect to the mysql instance using the mysql command if you have it installed on your local machine: `mysql --host=127.0.0.1 --port=3310 -uroot -p`
Or you could get shell access inside the docker container: `docker exec -it <container_name> bash` and then run `mysql -p` inside the container.

##### Stop ec-mysql container
``
docker stop ec-mysql
``
##### (ReStart) ec-mysql container
``
docker start ec-mysql
``
##### Remove ec-mysql container (must stop it first)
``
docker rm ec-mysql
``
##### Remove image (must stop and remove container first)
``
docker rmi mysql:latest
``
#### Startup with Profile settings
##### Default profile, H2 database
``
mvn spring-boot:run
``

or

``
java -jar target/explorecali-2.0.0-SNAPSHOT.jar
``
#### Startup with Profile settings
##### Default profile, H2 database
``
mvn spring-boot:run
``

or

``
java  -jar target/explorecali-2.0.0-SNAPSHOT.jar
``
##### mysql profile, MySql database (requires running container ec-mysql)
``
mvn spring-boot:run -Dspring.profiles.active=mysql
``

or

``
java  -Dspring.profiles.active=mysql -jar target/explorecali-2.0.0-SNAPSHOT.jar
``
#### App Dockerization

##### Build jar, image, set default profile
``
mvn package -DskipTests docker:build
``
###### container with default property set in Dockerfile
``
docker run --name ec-app-default -p 8100:8100  -d explorecali-default
``
##### Build jar, image, set mysql profile
``
mvn package -DskipTests docker:build -Dec-profile=mysql
``
##### Run Docker container with mysql profile
``
docker run --name ec-app-mysql -p 8200:8100  --link ec-mysql:mysql -d explorecali-mysql
``
##### Build jar, image, set docker profile
``
mvn package -DskipTests docker:build -Dec-profile=docker
``
##### Run Docker container with docker profile set in Dockerfile and migration scripts on host
``
docker run --name ec-app-docker -p 8300:8100 -v ~/db/migration:/var/migration -e server=ec-mysql -e port=3306 -e dbuser=cali_user -e dbpassword=cali_pass --link ec-mysql:mysql -d explorecali-docker
``
##### Enter Docker container
``
docker exec -t -i ec-app /bin/bash
``

##### Push image to Docker hub
######Login to Docker hub locally
``docker login``
###### Upload image
``
docker tag <image id> <docker hub repository>/explorecali-default:latest
``
###### Download image
``
docker pull <docker hub repository>/explorecali-default
``
##### Run Container from docker hub image
``
docker run --name ec-app-default -p 8100:8100  -d <docker hub repository>/explorecali-default``

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/#build-image)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#howto-use-exposing-spring-data-repositories-rest-endpoint)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

