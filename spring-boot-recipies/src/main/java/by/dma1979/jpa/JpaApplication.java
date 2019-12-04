package by.dma1979.jpa;

import by.dma1979.jpa.spring.SpringDataJpaCustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EntityScan({
        "by.dma1979.entity",
        "by.dma1979.jpa.entity" })
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}

@Component
class CustomerLister implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ICustomerRepository plainRepository;

    private final SpringDataJpaCustomerRepository springDataJpaCustomerRepository;

    private final ICustomerRepository plainHibernateCustomerRepository;

    CustomerLister(PlainJpaCustomerRepository plainRepository,
                   SpringDataJpaCustomerRepository springDataJpaCustomerRepository,
                   PlainHibernateCustomerRepository plainHibernateCustomerRepository) {
        this.plainRepository = plainRepository;
        this.springDataJpaCustomerRepository = springDataJpaCustomerRepository;
        this.plainHibernateCustomerRepository = plainHibernateCustomerRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        logger.debug("### Own Plain JPA repository ###");
        plainRepository.findAll().forEach(customer -> logger.info("{}", customer));

        logger.debug("### Spring Data JPA repository ###");
        springDataJpaCustomerRepository.findAll().forEach(customer -> logger.info("{}", customer));

        logger.debug("### Plain Hibernate repository ###");
        plainHibernateCustomerRepository.findAll().forEach(customer -> logger.info("{}", customer));
    }
}
