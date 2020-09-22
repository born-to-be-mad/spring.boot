# Run mongo as docker container:
```batch
docker run --name my-mongo-db -p 27017:27017 -d mongo:3.6
```
or
```batch
docker build -t my-mongo-db .
docker run -p 27017:27017 --name mongodb -it my-mongo-db
```