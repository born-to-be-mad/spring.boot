# Getting Started

## Configuration

### Native image support on Windows
* `sdk use java 22.3.r17-grl` to use GraalVM
* check java version 
    ```
    $ java --version
    openjdk 17.0.5 2022-10-18
    OpenJDK Runtime Environment GraalVM CE 22.3.0 (build 17.0.5+8-jvmci-22.3-b08)
    OpenJDK 64-Bit Server VM GraalVM CE 22.3.0 (build 17.0.5+8-jvmci-22.3-b08, mixed mode, sharing)
    ```
* `gu.cmd install native-image` to install Native Image
  * GraalVM comes with a package manager, gu, that downloads and installs packages not included in the core distribution
  * Result
    ```bash
    Downloading: Component catalog from www.graalvm.org
    Processing Component: Native Image
    Downloading: Component native-image: Native Image from github.com
    Installing new component: Native Image (org.graalvm.native-image, version 22.3.0)
    ```
* `gu.cmd list` to check for installed features to see if Native Image was added
    ```bash
    ComponentId              Version             Component name                Stability                     Origin
    ---------------------------------------------------------------------------------------------------------------------------------
    graalvm                  22.3.0              GraalVM Core                  Supported
    native-image             22.3.0              Native Image                  Early adopter                 github.com
    
    ```
* Native Image requires Visual Studio Code and Microsoft Visual C++(MSVC).
  * `C:\Program Files (x86)\Microsoft Visual Studio\2022\BuildTools\VC\Auxiliary\Build\vcvars64.bat` use this command if you have the Visual Studio Build Tools installed
  * `C:\Program Files (x86)\Microsoft Visual Studio\2022\Community\Common7\Tools\vcvars64.bat` use this command if you installed the full VS Code 2022
  * or simple launch from Windows `X64 Native Toools Command Prompt`
    ```bash
    **********************************************************************
    ** Visual Studio 2019 Developer Command Prompt v16.11.13
    ** Copyright (c) 2021 Microsoft Corporation
    **********************************************************************
    [vcvarsall.bat] Environment initialized for: 'x64'
    
    C:\Windows\System32>java --version
    openjdk 17.0.5 2022-10-18
    OpenJDK Runtime Environment GraalVM CE 22.3.0 (build 17.0.5+8-jvmci-22.3-b08)
    OpenJDK 64-Bit Server VM GraalVM CE 22.3.0 (build 17.0.5+8-jvmci-22.3-b08, mixed mode, sharing) 
    ```

### How to run

* `docker-compose up -d` to run kafka locally via docker.
* Building a Native Image Using `Buildpacks`
  * `mvn -Pnative -DskipTests spring-boot:build-image` to build the image, you can run the spring-boot:build-image goal with the native profile active
  * `docker run --rm -p 8080:8080 docker.io/library/kafka-producer:0.0.1-SNAPSHOT`
  * `ctrl-c` to gracefully exit the application
* Building a Native Image using `Native Build Tools`
  * GraalVM or Liberica Native Image Kit in
    version 22.3, the Visual Studio Build Tools and the Windows SDK are required on Windows
  * `spring-boot-starter-parent` inherits the `native` profile
  * `mvn -DskipTests clean package` to build the project 
  * `mvn -Pnative native:compile` to create native image executable under `target` by using `X64 Native Toools Command Prompt`. It will take approximately about 5-7 minutes.
  * `.\target\kafka-producer.exe` to run the nativa application.

### Reference Documentation

For further reference, please consider the following sections:

* [Set up a Kafka broker](https://developer.confluent.io/quickstart/kafka-docker/)
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/maven-plugin/reference/html/#build-image)
* [Spring Integration Test Module Reference Guide](https://docs.spring.io/spring-integration/reference/html/testing.html)
* [Spring Integration Apache Kafka Module Reference Guide](https://docs.spring.io/spring-integration/reference/html/kafka.html)
* [Cloud Stream](https://docs.spring.io/spring-cloud-stream/docs/current/reference/html/spring-cloud-stream.html#spring-cloud-stream-overview-introducing)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#messaging.kafka)
* [Spring Integration](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#messaging.spring-integration)

### Guides

The following guides illustrate how to use some features concretely:

* [Integrating Data](https://spring.io/guides/gs/integration/)

