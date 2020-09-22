* By default, Spring Boot creates a JAR or WAR and that can be run with `java -jar yourapplication.jar`. 
* In the Maven plugin you can set the executable property to true and the archive will
  be executable. After building your artifact, the artifact itself is executable and can be used
  to launch the application by simply typing `./your-application.jar`.
* Ton see the bash script `head -n 290 your-application.jar`