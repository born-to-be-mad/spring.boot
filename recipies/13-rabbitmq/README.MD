# Run as docker container:
```batch
docker run --name my-rabbitmq -p 5672:5672 -p 15672:15672 -d rabbitmq:management-alpine
```

* You can check in the RabbitMQ management console (default http://localhost:15672) the number of messages on the queue.`