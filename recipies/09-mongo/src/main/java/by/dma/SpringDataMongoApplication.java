package by.dma;

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

/**
 * @author : Dzmitry Marudau
 * @created at : 00:24
 * @since : 2020.05
 **/
@SpringBootApplication
public class SpringDataMongoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(
        SpringDataMongoApplication.class, args);
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
  public void run(ApplicationArguments args) throws Exception {

    List.of(
        new Customer("Marten Deinum", "marten.deinum@conspect.nl"),
        new Customer("Josh Long", "jlong@pivotal.io"),
        new Customer("Dzmitry Marudau", "vinmaster@tut.by"),
        new Customer("Tagir Valeev", "amaembo@gmail.com"))
        .forEach(repository::save);
  }
}

@Component
class MongoCustomerLister implements ApplicationRunner {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final MongoCustomerRepository repository;

  MongoCustomerLister(MongoCustomerRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(ApplicationArguments args) {

    repository.findAll()
              .forEach(customer -> logger.info("{}", customer));
  }
}