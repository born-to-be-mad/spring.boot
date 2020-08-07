# Spring Boot Actuator
If Spring Boot has the actuator configured, than the app is accessible through JMX.
As result it is possible to use `jconsole` to manage the application
and through the web (default under the `/actuator` path).

JMX exposes a lot more endpoints then HTTP does. 
HTTP only exposes `/actuator/health` and `/actuator/info`. JMX exposed a lot more. 
This is done with security in mind: the `/actuator` is exposed publicly 
and as such you don’t want everyone to see things. 
What to expose can be configured through the management by using properties:
* `endpoints.web.exposure.include` 
* `management.endpoints.web.exposure.exclude`
Using a * for the include will expose all endpoints to the web.

## Configuration
Running the actuator on a different port has the benefit of hiding it from the public internet 
by blocking the port on the firewall and only allowing local access.

The `management.server` properties are only effective when using an embedded server; 
when deploying to an external server these properties don’t apply anymore!

Individual endpoints can be configured through properties in the `management.endpoint.<endpoint-name>` namespace:
* `enabled` property
* `cache.time-to-live` property 
* more

### Securing Management Endpoints
When Spring Boot detects both Spring Boot Actuator and Spring Security, 
it will enable secure access to management endpoints automatically. 

Adding the `spring-boot-starter-security` in addition to the `Configure Healthchecksspring-bootstarter-actuator` is enough to have secured management endpoints.

### Configure Healthchecks
Which HealthIndicators are present can be controlled by setting the `management.health.<health-indicator>.enabled` property.

### Configure Metrics
One of the features of Spring Boot Actuator is to expose metrics. 
`/actuator/metrics/{name-of-metric}` to obtain information about metric by name.

Spring Boot uses micrometer.io1 to record metrics. Metrics are enabled by default
for the features detected by Spring Boot. So if a DataSource is detected, metrics will be
enabled. To disable metrics, add them to the `management.metrics.enable property`.