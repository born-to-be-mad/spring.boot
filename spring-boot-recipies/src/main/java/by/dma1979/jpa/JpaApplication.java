package by.dma1979.jpa;

import by.dma1979.jpa.spring.SpringDataJpaCustomerRepository;
import org.hibernate.cfg.AvailableSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
@EntityScan({
        "by.dma1979.entity",
        "by.dma1979.jpa.entity"})
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean(name = "entityManagerFactory")
    LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        Properties properties = new Properties();
        properties.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.setProperty(AvailableSettings.NON_CONTEXTUAL_LOB_CREATION, "true");
        //properties.setProperty(AvailableSettings.CONNECTION_PROVIDER_DISABLES_AUTOCOMMIT, "true");

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("by.dma1979.jpa", "by.dma1979.jpa.entity");
        sessionFactoryBean.setHibernateProperties(properties);
        return sessionFactoryBean;
    }

//  @Bean
//  public PlatformTransactionManager transactionManager(SessionFactory sf) {
//	  return new HibernateTransactionManager(sf);
//  }
}

@Component
class CustomerLister implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ICustomerRepository plainRepository;

    private final SpringDataJpaCustomerRepository springDataJpaCustomerRepository;

    private final ICustomerRepository plainHibernateCustomerRepository;

    private final ICustomerRepository nativeHibernateCustomerRepository;

    CustomerLister(PlainJpaCustomerRepository plainRepository,
                   SpringDataJpaCustomerRepository springDataJpaCustomerRepository,
                   PlainHibernateCustomerRepository plainHibernateCustomerRepository,
                   NativeHibernateCustomerRepository nativeHibernateCustomerRepository) {
        this.plainRepository = plainRepository;
        this.springDataJpaCustomerRepository = springDataJpaCustomerRepository;
        this.plainHibernateCustomerRepository = plainHibernateCustomerRepository;
        this.nativeHibernateCustomerRepository = nativeHibernateCustomerRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        logger.debug("### Own Plain JPA repository ###");
        plainRepository.findAll().forEach(customer -> logger.info("{}", customer));

        logger.debug("### Spring Data JPA repository ###");
        springDataJpaCustomerRepository.findAll().forEach(customer -> logger.info("{}", customer));

        logger.debug("### Plain Hibernate repository ###");
        plainHibernateCustomerRepository.findAll().forEach(customer -> logger.info("{}", customer));

        logger.debug("### Plain NativeHibernate repository ###");
        nativeHibernateCustomerRepository.findAll().forEach(customer -> logger.info("{}", customer));
    }
}
