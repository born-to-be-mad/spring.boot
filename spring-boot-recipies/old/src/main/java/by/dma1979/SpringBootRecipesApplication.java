package by.dma1979;

import by.dma1979.calculator.Calculator;
import by.dma1979.entity.Book;
import by.dma1979.exception.CustomizedErrorAttributes;
import by.dma1979.jdbc.CustomerRepository;
import by.dma1979.jpa.ICustomerRepository;
import by.dma1979.jpa.NativeHibernateCustomerRepository;
import by.dma1979.jpa.PlainHibernateCustomerRepository;

import by.dma1979.jpa.PlainJpaCustomerRepository;
import by.dma1979.jpa.spring.SpringDataJpaCustomerRepository;
import by.dma1979.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.cfg.AvailableSettings;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;

@SpringBootApplication
public class SpringBootRecipesApplication implements WebMvcConfigurer {

    private static final Logger LOG = LogManager.getLogger(SpringBootRecipesApplication.class);

    /*
    Changing a Use`r's Locale.
    We register LocaleChangeInterceptor as interceptor via WebMvcConfigurer.
    @param registry the registry of interceptors
    */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootRecipesApplication.class, args);
        LOG.info("Hello from Log4j 2 - ConfigurableApplicationContext : {}", () -> context);
        System.out.println("###############   BOOTING........");
        if (isDebugMode(args)) {
            System.out.println("### DEBUG MODE IS ACIVATED ....");
            printBeanDefinitions(context);
        }

        LOG.info("###############   THE END    ###############");
    }

  private static boolean isDebugMode(String[] args) {
    return args.length > 0 && args[0].equalsIgnoreCase("-debug");
  }

    private static void printBeanDefinitions(ConfigurableApplicationContext context) {
        System.out.printf("# There are %d bean definitions:%n", context.getBeanDefinitionCount());
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        Arrays.asList(beanNames).forEach(System.out::println);
    }

    @Bean
    public ApplicationRunner printDBContentViaCustomerRepository(CustomerRepository repository) {
        return args -> {
            System.out.println("### Print DB content via CustomerRepository ...");
            repository.findAll()
                .forEach(customer -> LOG.info("{}", customer));
        };
    }

    @Bean
    public ApplicationRunner printDBContentViaJDBCTemplate(JdbcTemplate jdbc) {
        return args -> {
            System.out.println("### Print DB content via JdbcTemplate ...");
            jdbc.query("SELECT id, name, email FROM customer",
                    rs -> {
                        LOG.info("Customer [id={}, name={}, email={}]",
                                rs.getLong(1), rs.getString(2), rs.getString(3));
                    });
        };
    }

    @Bean
    public ApplicationRunner printDBContentViaDataSource(DataSource dataSource) {
        return args -> {
            System.out.println("### Print DB content via DataSource ...");
            try (
                    var con = dataSource.getConnection();
                    var stmt = con.createStatement();
                    var rs = stmt.executeQuery("SELECT id, name, email FROM customer")
            ) {
                while (rs.next()) {
                    LOG.info("Customer [id={}, name={}, email={}]",
                            rs.getLong(1), rs.getString(2), rs.getString(3));
                }
            }
        };
    }

    @Bean
    public ApplicationRunner printConnectionMetaData(DataSource dataSource) {
        return args -> {
            if (isDebugMode(args.getSourceArgs())) {
                System.out.println("### Print data source connection metadata ...");
                try (var con = dataSource.getConnection();
                     var rs = con.getMetaData().getTables(null, null, "%", null)) {
                    while (rs.next()) {
                        LOG.info("{}", rs.getString(3));
                    }
                }
            }
        };
    }

    @Bean
    public ApplicationRunner booksInitializer(BookService bookService) {
        System.out.println("### Initializing books ...");
        return args -> {
            bookService.create(
                    new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee"));
            bookService.create(
                    new Book("9780451524935", "1984", "George Orwell"));
            bookService.create(
                    new Book("9780618260300", "The Hobbit", "J.R.R. Tolkien"));
        };
    }

    @Bean
    public ApplicationRunner calculationRunner(Calculator calculator,
                                               @Value("${lhs}") int lhs,
                                               @Value("${rhs}") int rhs,
                                               @Value("${op:+}") char op) {
        System.out.println("### Calculator calculate ...");
        return args -> calculator.calculate(lhs, rhs, op);
    }

    @Bean
    public HandlerInterceptor localeChangeInterceptor() {
        return new LocaleChangeInterceptor();
    }

    /*
    CookieLocaleResolver resolves locales by inspecting a cookie in a user’s browser.<BR/>
    If the cookie does not exist, this locale resolver determines the default locale from the Accept-Language HTTP header.
    */

    @Bean
    public LocaleResolver localeResolver() {
/*        AbstractLocaleContextResolver localeResolver = new SessionLocaleResolver(); // FixedLocaleResolver
        localeResolver.setDefaultLocale(new Locale("ru"));
        return localeResolver;*/

        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        //we can customize
        //cookieLocaleResolver.setCookieName("language");
        cookieLocaleResolver.setCookieMaxAge(3600); //1 hour
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return cookieLocaleResolver;
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

    @Bean
    public CustomizedErrorAttributes errorAttributes() {
        return new CustomizedErrorAttributes();
    }


}

@Component
class CustomerLister implements ApplicationRunner {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

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
