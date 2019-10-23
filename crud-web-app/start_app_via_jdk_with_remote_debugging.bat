
set JAVA_HOME=d:\BIN\Java\openjdk-13.24

rem Source: https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#using-boot-running-as-a-packaged-application
%java_home%\BIN\java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=5005,suspend=n -jar target/crud-web-app-0.0.1-SNAPSHOT.jar