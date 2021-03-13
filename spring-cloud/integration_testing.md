# Integration Tests

*Characteristics*: deep, slow, fragile/flaky
*Where*: files, databases, queues

## Test Execution

* using test profiles, f.e. H2 database with active 'ps6spy' to proxy all jdbc calls
@ActiveProfiles("db-mem") with the separate file 'application-db-mem.properties/application-db-mem.yml'
@ActiveProfiles("db-h2") with the separate file 'application-db-h2.properties/application-db-h2.yml'
```properties
#spring.datasource.url=jdbc:h2:tc[://localhost:9082/~/test
#driver-class-name=orh.h2.Driver
spring.datasource.url=jdbc:p6spy:h2:tc[://localhost:9082/~/test
driver-class-name=com.ps6spy.engine.spy.P6SpyDriver
spring.datasource.username=sa
spring.datasource.password=sa
spring.jpa.hibernate.ddl-auto=create
```

* CleanupBeforeTest for some cases

* clear all necessary table by executing scripts from test resources
```java
@Sql(scripts = "classpath:/clear-data.sql", executionPhase = ExecutionPhase.BEFORE_EACH)
```

* re-create the context for every test - to avoid interference between tests, but performance is slower
```java
@DirtyContext(classMode = ClassMode.BEAFORE_EACH_TEST_METHOD)
```

* use @Transactional on the test to run each test in a Transactional
```java
@SpringBootTest
@ActiveProfiles("db-h2")
@Transactional
```

* use Assertions in @BeforeEach method to be sure that system in correct state,
  ```xml
  <dependency>
    <groupId>org.assertj</groupId>
    <artifactId>assertj-code</artifactId>
  </dependency>
  ```
  it is useful for real-world projects, f.e. nested transactions
```java
    @BeforeEach
    public final void before() {
        assertThat(repo.findAll()).isEmpty();
        asssertTha(scoreStr).isEqualTo("Test");
        //collections
        asssertTha(list).anyMatch(e -> e.getX() == 1);
        asssertTha(list).doesNotContain(2);
    }
```

* useful arguments to run tests and speed up them dramatically
`-ea -noverify -mx2048m -Xverify:none -XX:TieredStopAtLevel=1`

* dockerize dependencies in case of DB specific features when you can't use H2 and real DB is needed,
  then use specific @Tag and use docker and separate mvn/gradle task which start docker container with DB
```java
@Tag("integration")
@ContextConfiguration(initializers = WaitForDatabase.class)
@SpringBootTest
```

* use @MockBean which overrides the @Bean with mock functionality


## Test Data

* create base class RepoTestBase(junit 4), it can also hold all useful annotations @SpringBootTest, @Transactional, @ActiveProfile
```java
public abstract class RepoTestBase{
    @BeforeEach
    public final void populateSomeData() {
    }
}
```

* use extensions form Junit5
```java
public class CommonDataExtension implements BeforeEachCallback{
    @Override
    public final void beforeEach(ExecutionContext context) throws Exception {
        //save test data
    }
}

public class ClassUnderTest {
    //use extension in target class
    @RegisterExtension
    public CommonDataExtension data = new CommonDataExtension();
}
```

* insert via @Profile - a Spring bean persisting data at app startup

* use @Sql to populate data from test resources
* `@Sql` =  TestClass.testMethod.sql
* `@Sql("data.sql)`
* `@Sql({"schema.sql", "data.sql"})`


## Integration in Action

* Cucumber and Spring Integration via Gherkin Language

* override properties, WireMockExtension and mappings
```java
@SpringBootTest(properties = "safety.service.url.base=http://localhost:8099")

@RegisterExtension
public WireMockExtension wireMock = new WireMockExtension(8099);
// it really starts the http server which  listens to the mocked port and responds with some data
```
+ files 'resources\mappings\urlName-SAFE.json' and 'resources\mappings\urlName-UNSAFE.json'containing "request" and "Response"

* wiremock recordings
  `java -jar wiremock-standalone-2.27.2.jar` to start the server and record http calls
  * go to http://wiremock.org/docs/running-standalone/
  * download
  * run
  * go to http://localhost:8080/__admin/recorder
  * start recording
  * point yur app/test to localhost:8080 (wiremock proxy)
