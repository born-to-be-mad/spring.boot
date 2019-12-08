# How to run DB
### Compile docker image with postgress
`docker build -t sb2r-postgres.`

###  run POSTGRES IMAGE using 
`docker run -p 5432:5432 -it sb2r-postgres`.

###  run MONGO DB IMAGE using 
`docker run -p 27017:27017 -it sb2r-mongodb`.


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/maven-plugin/)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#using-boot-devtools)

