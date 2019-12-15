#!/bin/bash

#run container default
docker run -d -p 27017:27017 -v ~/data:/data/db mongo
#docker run -d -p 27017:27017 -v ~/data:/data/db mongo:3.6-xenial
#docker run --name sb2r-mongodb -p 27017:27017 -d mongo:3.6

#stop all containers
#sudo docker stop $(sudo docker ps -q)

# Install the MongoDB client
#sudo apt-get install mongodb-clients
# Change mydb to the name of your DB
#mongo localhost/mydb



