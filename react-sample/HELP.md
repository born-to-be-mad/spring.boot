# Getting Started

# Frontend part was created via 'yearn' 
```
yarn create react-app frontend
```
#Run in dev-mode
## Run spring boot application(port 8888)
Check: http://localhost:8888/apply?action=Hi&destination=Dimas
## Run Frontend application(port 3000)
```
yarn start
```
Check: http://localhost:3000

#Run in production
```
mvnw spring-boot:run -Pprod
```
Check: http://localhost:8888 
index.html will be opened with react application which deals with spring boot server

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

