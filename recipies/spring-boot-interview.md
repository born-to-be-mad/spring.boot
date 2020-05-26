# Interview questions
+ [What is __Spring Boot__?](#What-is-Spring-Boot)
+ [What is __spring-boot-starter-parent__?](#What-is-spring-boot-starter-parent)
+ [Annotations to run spring boot application?](#Annotations-to-run-spring-boot-based-application)
+ [What is __@SpringBootApplication__?](#What-is-@SpringBootApplication)
+ [What is SpringApplication.run?](#What-is-SpringApplication.run)
+ [What is the interface ApplicationRunner?](#What-is-interface-ApplicationRunner)
+ [How to use @Value?](#How-to-use-@Value?)
+ [What is @PropertySource annotation?](#What-is-@PropertySource-annotation)

## What is _Spring Boot_?
*Spring Boot Reference Guide*:
  __Spring Boot__ makes it easy to create stand-alone, production-grade, Springbased Applications that you can “just run.” We take an opinionated view of
the Spring platform and third-party libraries so you can get started with
minimum fuss. Most Spring Boot applications need very little Spring
configuration.

[Table of contents](#Interview-questions)

## What is _spring-boot-starter-parent_?
The _spring-boot-starter-parent_ is used as the parent for the
spring boot application.
The _spring-boot-starter_ will pull in all the core dependencies needed to
start a very basic Spring Boot application, things like the Spring Framework, Logback for
logging, and Spring Boot itself.

[Table of contents](#Interview-questions)

## Annotations to run Spring Boot-based application?
* The __@Configuration__ annotation makes this class a Spring Java configuration class.
Generally when creating an application you also have other components you need to have picked up -
for this add the __@ComponentScan__ annotation.
Finally, to let Spring Boot do its auto-configuration, we need the __@EnableAutoConfiguration__ annotation.
* The @SpringBootApplication annotation is a so-called composed annotation and consist of 3 annotations:
  * @Configuration
  * @ComponentScan
  * @EnableAutoConfiguration

[Table of contents](#Interview-questions)

## What is @SpringBootApplication?
* The @SpringBootApplication annotation is a so-called composed annotation and consist of 3 annotations:
  * @Configuration
  * @ComponentScan
  * @EnableAutoConfiguration

The _@SpringBootConfiguration_ is a specialized @Configuration annotation.
It indicates that this is a Spring Boot-based application.
When using @SpringBootConfiguration in your application, there can only be one class annotated with this annotation!

[Table of contents](#Interview-questions)

## What is the difference between @SpringBootApplication and @Configuration?
The _@SpringBootConfiguration_ is a specialized @Configuration annotation.
It is composed annotation(@Configuration, @ComponentScan, @EnableAutoConfiguration etc.).
It indicates that this is a Spring Boot-based application.
When using @SpringBootConfiguration in your application, there can only be one class annotated with this annotation!

[Table of contents](#Interview-questions)

## What is SpringApplication.run?
it is a static helper that can be used to run a SpringApplication
from the specified source using default settings.

The main method calls SpringApplication.run with the main class passed as first parameter
and arguments from the main method. The `run` method returns an ApplicationContext.

[Table of contents](#Interview-questions)

##What is the interface ApplicationRunner?
Spring Boot has an interface `ApplicationRunner`, which can be used
to run some code after startup of the application.
When Spring Boot detects a bean of type ApplicationRunner,
it will invoke its run method as soon as the application has started.

[Table of contents](#Interview-questions)

##How to use @Value?
The _@Value_ will instruct Spring to look up the property and use the value of that property.
For instance if we would use a @Value("${test}"), Spring would try to detect a property named `test`
and use the value.
You could also specify a default value by adding a semicolon, f.e. @Value("${test:123}").

[Table of contents](#Interview-questions)

##What is _@PropertySource_ annotation?
The _@PropertySource_ annotation allows you to add additional property files to be loaded during startup.

[Table of contents](#Interview-questions)

## What is @MockBean annotation?
Spring Boot makes it easy to replace a bean with a mock in the application context via @MockBean annotation.
When there are multiple beans of a certain type, you need to specify the name of the bean you wish to replace.

[Table of contents](#Interview-questions)

## What is the purpose of @Import or @ImportResource?
@Import or @ImportResource annotation allows @Configuration or @SpringBootApplication annotated class
to import the configuration. It lets Spring loads the additional files.
When bootstrapping the application, Spring Boot will also load the additional configuration from the mentioned XML file.

```java
@ImportResource("classpath:application-context.xml")
// OR
@Import(ExistingConfiguration.class)
```

[Table of contents](#Interview-questions)

## How to replace @RestController annotation?
Instead of @RestController you could also use @Controller and put @ResponseBody on each request
handling method. Using @RestController will implicitly add @ResponseBody to request handling methods.

[Table of contents](#Interview-questions)
