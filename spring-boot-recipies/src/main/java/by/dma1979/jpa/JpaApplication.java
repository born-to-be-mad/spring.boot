package by.dma1979.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}

@Component
class CustomerLister implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final by.dma1979.jpa.CustomerRepository repository;
    private final by.dma1979.jpa.spring.CustomerRepository crudRepository;

    CustomerLister(CustomerRepository repository,
                   by.dma1979.jpa.spring.CustomerRepository crudRepository) {
        this.repository = repository;
        this.crudRepository = crudRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        logger.debug("### Own JPA repository");
        repository.findAll().forEach(customer -> logger.info("{}", customer));
        logger.debug("### Spring CRUD repository");
        crudRepository.findAll().forEach(customer -> logger.info("{}", customer));
    }
}
