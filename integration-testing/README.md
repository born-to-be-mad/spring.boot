#Test Libraries
* Spring Boot defaults: jUnit5, Mockito, AssertJ, JSONAssert, JsonPath
* Mockito
* BDDMockito - reduce boilerplate, improved code readability
* AssertJ - rich set of assertions, helpful error messages, improved code readability, asy to use
* @Testcontainers - simple setup, throwaway docker DB instance

### Mocking in Spring boot
* @MockBean
* @SpyBean

### Mockito
```java
    when(methodCall).then(doSomething);
```

### BDDMockito
```java
    given(methodCall).will(doSomething);
```

### AssertJ
```java
    assertThat(entity.getName())
        .as("Check that Name is set")
        .isEqualTo("Ivan");

    assertThat(list)
    .isNotEmpty()
    .doesNotContainNull()
    .containsSequence("2", "3");

    assertThatThrownBy(() -> {})
    .isInstanceOf(Exception.class)
    .hasMessageContaining("boom");

    //BDDAssertions
    then(entity.getAge()).isEqualTo(30);
```

## Test Slices
* DATA
  * @DataJdbcTest
  * @DataJpaTest
  * @DataLdapTest
  * @DataMongoTest
  * @DataNeo4jTest
  * @DataRdbcTest
  * @DataRedisTest
* WEB
  * @RestClientTest
  * @WebServiceClientTest
  * @WebFluxTest
  * @WebMvcTest
* SERIALIZATION
  * @JsonTest


# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#using-boot-devtools)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

