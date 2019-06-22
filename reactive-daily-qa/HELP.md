# Getting Started

## Run Postpres as docker container
* download docker image
```
$ pull postgres:11
```

* run postgres container with database port and user password
```
$ docker run --name dev-postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres:11
```

* create database 'dailyqa' for user 'postgres'
```
$ docker exec dev-postgres psql -U postgres -c "create database dailyqa" postgres
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

Based on [Josh Long speech at Voxxed Days Minsk 2019](https://www.youtube.com/watch?v=Z5q-CXbvM1E&list=PLRsbF2sD7JVq3tPa0jQjCtI1_xeLiPu-Z&index=32)