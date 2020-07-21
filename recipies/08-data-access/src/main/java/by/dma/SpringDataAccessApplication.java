package by.dma;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import by.dma.jdbc.repository.CustomerRepository;

import static by.dma.SpringDataAccessApplication.SELECT_SQL;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:24
 * @since : 2020.05
 **/
@SpringBootApplication
public class SpringDataAccessApplication {
  public static final String SELECT_SQL = "SELECT id, name, email FROM customer";

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(
        SpringDataAccessApplication.class, args);
  }
}

@Component
class JdbcRepositoryTableLister implements ApplicationRunner {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final CustomerRepository customerRepository;

  JdbcRepositoryTableLister(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public void run(ApplicationArguments args) {
    System.out.println("###### DB content via Jdbc repository -> START ######");
    customerRepository.findAll()
                      .forEach(customer -> logger.info("{}", customer));

  }
}

@Component
class JdbcTemplateTableLister implements ApplicationRunner {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final JdbcTemplate jdbc;

  JdbcTemplateTableLister(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public void run(ApplicationArguments args) {
    System.out.println("###### DB content via JdbcTemplate -> START ######");
    jdbc.query(SELECT_SQL, rs -> {
      logger.info("Customer [id={}, name={}, email={}]",
                  rs.getLong(1),
                  rs.getString(2),
                  rs.getString(3));
    });
  }
}

@Component
class CustomerDataSourceLister implements ApplicationRunner {
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final DataSource dataSource;

  CustomerDataSourceLister(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println("###### DB content via DATASOURCE -> METADATA ######");
    try (
        var con = dataSource.getConnection();
        var rs = con.getMetaData()
                    .getTables(null, null, "%", null)) {
      while (rs.next()) {
        logger.info("{}", rs.getString(3));
      }
    }
    System.out.println("###### DB content via DATASOURCE -> START ######");
    try (
        var con = dataSource.getConnection();
        var stmt = con.createStatement();
        var rs = stmt.executeQuery(SELECT_SQL)
    ) {
      while (rs.next()) {
        logger.info("Customer [id={}, name={}, email={}]",
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3));
      }
    }
    System.out.println("###### DB content via DATASOURCE -> END ######");
  }
}
