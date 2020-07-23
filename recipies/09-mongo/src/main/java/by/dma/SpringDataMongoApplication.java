package by.dma;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import by.dma.entity.Customer;
import by.dma.repository.MongoCustomerRepository;
import by.dma.repository.SpringDataMongoRepository;
import by.dma.repository.SpringDataReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:24
 * @since : 2020.05
 **/
@SpringBootApplication
public class SpringDataMongoApplication {

  public static void main(String[] args) throws IOException {
    ConfigurableApplicationContext context = SpringApplication.run(SpringDataMongoApplication.class, args);
    // To prevent the shutdown of the application, add a System.in.read(); this will keep the application running
    // until you press Enter
    System.in.read();
  }
}

@Component
@Order(4)
class ReactiveDataInitializer implements ApplicationRunner {
  private final SpringDataReactiveMongoRepository repository;

  ReactiveDataInitializer(SpringDataReactiveMongoRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(ApplicationArguments args) {
    System.out.println("### -------------->");
    System.out.println("### -> REACTIVE ###");
    System.out.println("### -------------->");
    repository.deleteAll()
              .thenMany(
                  Flux.just(
                      new Customer("Marten Deinum", "marten.deinum@conspect.nl"),
                      new Customer("Josh Long", "jlong@pivotal.io"),
                      new Customer("Dzmitry Marudau", "vinmaster@tut.by"),
                      new Customer("Josh Long", "jlong@pivotal.io"),
                      new Customer("Dzmitry Marudau", "vinmaster@tut.by"),
                      new Customer("Tagir Valeev", "amaembo@gmail.com")
                  )
              ).delayElements(Duration.ofMillis(250))
              .flatMap(repository::save).subscribe(System.out::println);
  }
}

@Component
@Order(5)
class MongoSpringDataReactiveCustomerLister implements ApplicationRunner {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final SpringDataReactiveMongoRepository repository;

  MongoSpringDataReactiveCustomerLister(SpringDataReactiveMongoRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(ApplicationArguments args) {
    System.out.println("### --> SpringDataReactiveMongoRepository");
    repository.findAll().subscribe(customer -> logger.info("{}", customer));
  }
}

@Component
@Order(1)
class DataInitializer implements ApplicationRunner {

  private final MongoCustomerRepository repository;

  DataInitializer(MongoCustomerRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(ApplicationArguments args) {

    List.of(
        new Customer("Marten Deinum", "marten.deinum@conspect.nl"),
        new Customer("Josh Long", "jlong@pivotal.io"),
        new Customer("Dzmitry Marudau", "vinmaster@tut.by"),
        new Customer("Tagir Valeev", "amaembo@gmail.com"))
        .forEach(repository::save);
  }
}

@Component
@Order(2)
class MongoRepositoryCustomerLister implements ApplicationRunner {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final MongoCustomerRepository repository;

  MongoRepositoryCustomerLister(MongoCustomerRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(ApplicationArguments args) {
    System.out.println("### -> MongoCustomRepository");
    repository.findAll()
              .forEach(customer -> logger.info("{}", customer));
  }
}

@Component
@Order(3)
class MongoSpringDataCustomerLister implements ApplicationRunner {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final SpringDataMongoRepository repository;

  MongoSpringDataCustomerLister(SpringDataMongoRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(ApplicationArguments args) {
    System.out.println("### -> MongoSpringDataRepository");
    repository.findAll()
              .forEach(customer -> logger.info("{}", customer));
  }
}