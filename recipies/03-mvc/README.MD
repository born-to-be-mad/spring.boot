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