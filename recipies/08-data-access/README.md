
# Run postgres as docker container:
```batch
docker run --name my-postgress -e POSTGRES_USER=customers -e POSTGRES_PASSWORD=customers -e POSTGRES_DB=customers -p 5432:5432 -d --rm postgres:alpine
```
or
```batch
docker build -t my-postgres-db .
docker run -p 5432:5432 --name my-postgress -it my-postgres-db
```
