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

* use assertions in @BeforeEach method to be sure that system in correct state,
  it is useful for real-world projects, f.e. nested transactions
```java
    @BeforeEach
    public final void before() {
        assertThat(repo.findAll()).isEmpty();
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
* Cucumber and Spring Integration

