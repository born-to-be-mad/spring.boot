# Run MySQL

* `docker run --name tdd-workshop-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root-pw -d mysql:latest` to run a MySQL
  container without database creation.
* `docker run --name tdd-workshop-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root-pw -e MYSQL_DATABASE=tdd-workshop -e MYSQL_USER=tdd -e MYSQL_PASSWORD=workshop -d mysql:latest`
  to run a MySQL container with database creation.
* `docker logs tdd-workshop-mysql` to see the logs of the container.
* `docker exec -it tdd-workshop-mysql bash` to get a shell in the container.
* `docker stop tdd-workshop-mysql` to stop the container.
* `docker start tdd-workshop-mysql` to start the container.
* `docker rm -f tdd-workshop-mysql` to remove the container.

Modify the `application.yaml` files to have the correct database connection URL with accurate schema name and port
number; plus correct username and password values too, e.g.:

```
  spring.datasource.url = jdbc:mysql://localhost:3306/tdd-workshop
  spring.datasource.username = tdd
  spring.datasource.password = workshop
```