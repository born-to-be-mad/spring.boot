# Getting Started

* To Access h2 in-memory database use the link [localhost:port/h2-console](http://localhost:port/h2-console) with JDB URL `jdbc:h2:mem:testdb`

* API is available via [link](http://localhost:port/swagger-ui.html#)

### Creating entities include following steps:
- [X] Add test for creating new entities(Card, User)
- [X] Add the endpoint for creating new entities(Card, User)
- [X] Add business logic for creating new entities(Card, User)
- [X] Manually verify endpoints with Postman

### Reading entities include following steps:
- [X] Add test for reading entities
- [X] Add the endpoint for reading one and all entities
- [X] Add business logic for both endpoints
- [X] Manually verify endpoints with Postman

### Updating entities include following steps:
- [X] Add test for updating entities
- [X] Add the endpoint for updating the existing entities
- [X] Add business logic for updating the existing entities
- [X] Manually verify endpoints with Postman

### Deleting entities include following steps:
- [X] Add test for deleting entities
- [X] Add the endpoint for deleting the existing entities
- [X] Add business logic for deleting the existing entities
- [X] Manually verify endpoints with Postman

### Integration test
- [X] IT for Card REST service
- [X] IT for User REST service

### Hibernate steps
- [X] Many2Many - DB tables
  * [X] table User
    * [X] table User
  * [X] table Card
  * [X] table Card2user
  * [X] fill table data
- [ ] Many2Many - mapping entities
- [ ] Many2Many - test in Demo app

# Create frontend app
* Node.js version that is supported is 10.9 or greater

* run `npm install -g @angular/cli` to install Angular CLI(include routing and CSS)

* run `ng new frontend` to generate the entire application structure within the 'frontend' directory
** Angular’s application files use TypeScript, a typed superset of JavaScript that compiles to plain JavaScript.
** the entry point of any Angular application is a plain old index.html file.
** <app-root> is the root selector that Angular uses for rendering the application’s root component.
** add [Bootstrap](https://getbootstrap.com/) to index.html
```$
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
    crossorigin="anonymous">
```

## Run Frontend application 
* go to 'frontend' and run `ng serve`
  * This will start Angular’s live development server and also open the browser at http://localhost:4100.
* open http://localhost:4100/users to see the list of users.  


### Angular application 
  * app.component.ts - the app.component.ts Root Component
    * selector – the HTML selector used to bind the component to the HTML template file
    * templateUrl – the HTML template file associated with the component
    * styleUrls – one or more CSS files associated with the component
  * app.component.html -  allows us to define the root component’s HTML template
    * In each case, the HTML template associated with the matching component will be rendered within the <router-outlet></router-outlet> placeholder.

* go to 'frontend/src/app', create directory 'model'  and run 'ng generate class user'
  *  src/app/model/user.ts - empty User class.
  *  src/app/model/user.spec.ts  - test for new User class 

* go to 'frontend/src/app', create directory 'service'  and run  `ng generate service user` to generate user service that performs GET and POST requests to the http://localhost:8100/api/users endpoint.
  *  src/app/service/user.service.ts - empty User class.
  *  src/app/service/user.service.spec.ts  - test for new User class 
  *  @Injectable() metadata marker. This signals that the service should be created and injected via Angular’s dependency injectors.
  
* go to 'frontend/src/app', and run 'ng generate component user-list' to generate a user list component
  * src/app/user-list/user-list/user-list.component.html - simple html/bootstrap table which displays entities
  * src/app/user-list/user-list/user-list.component.ts - UserListComponent class.
  * src/app/user-list/user-list/user-list.component.spec.ts - test for new UserListComponent class
  * src/app/user-list/user-list/user-list.component.css - styles

* go to 'frontend/src/app', and run 'ng generate component user-form' to generate a user form
  * src/app/user-list/user-form/user-form.component.html - simple html/bootstrap form for persisting a new user in the database
  * src/app/user-list/user-form/user-form.component.ts - UserFormComponent  class.
  * src/app/user-list/user-form/user-form.component.spec.ts - test for new UserFormComponent  class
  * src/app/user-list/user-form/user-form.component.css - styles
  
* edit `app-routing.module.ts` to call our components when the user clicks the buttons in the navigation bar.
  * the Routes array instructs the router which component to display when a user clicks a link or specifies a URL into the browser address bar.
  * A route is composed of two parts:
    * Path –  a string that matches the URL in the browser address bar
    * Component – the component to create when the route is active (navigated)

* edit `app.module.ts` to call our components when the user clicks the buttons in the navigation bar.
  * import all the required modules, components, and services
  * also we need to specify which provider we’ll use for creating and injecting the UserService class to make Angular able  to inject it into the component classes

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

