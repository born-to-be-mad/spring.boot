# Getting Started
 -  server runs on the port 8888
 -  rsocket server runs on the port 7777
 - `http://localhost:8888/branches` to see the branches(typical REST call)
 - `http://localhost:8888/branch/names` to see the names of branches via WebClient(reactive call)
 - 'http://localhost:8888/index.html' here we can see WebSocket in action( pinging in browser console)
 
## Run Postgres as docker container
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

## Run Oracle as docker container
* login to Docker hub with your Docker account information with the below command
```
$ docker login
```

* Use the below command to install Oracle 12c image from Docker Hub.
```
$ docker run -d -p 1521:1521 --name OracleDB store/oracle/database-enterprise:12.2.0.1-slim
```

* to start and mount the database.
```
$ docker exec -it OracleDB bash -c "source /home/oracle/.bashrc; sqlplus /nolog"
``` 

* start working on DB
```
connect sys as sysdba;
-- Here enter the password as 'Oradoc_db1'
alter session set "_ORACLE_SCRIPT"=true;
create user dummy identified by dummy;
GRANT CONNECT, RESOURCE, DBA TO dummy;
--Now create a sample table.
create table Docker (id int,name varchar2(20));
--Start inserting values in to the table
```

* Below are the details of the database we installed:

  * HostName: localhost (hostname will be System IP address if you installed in any Linux VM)

  * Port: 1521

  * UserName and Password: dummy

  * Service Name: ORCLCDB.localdomain 

  * Use the below command to get the Service Name of the DB installed.
```
select value from v$parameter where name='service_names';
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

Based on [Josh Long speech at Voxxed Days Minsk 2019](https://www.youtube.com/watch?v=Z5q-CXbvM1E&list=PLRsbF2sD7JVq3tPa0jQjCtI1_xeLiPu-Z&index=32)