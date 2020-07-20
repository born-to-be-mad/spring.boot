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

import by.dma.repository.CustomerRepository;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:24
 * @since : 2020.05
 **/
@SpringBootApplication
public class SpringDataAccessApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(
            SpringDataAccessApplication.class, args);
  }
}

@Component
class TableLister implements ApplicationRunner {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final DataSource dataSource;
  private final JdbcTemplate jdbc;
  private final CustomerRepository customerRepository;

  TableLister(DataSource dataSource, JdbcTemplate jdbc,
          CustomerRepository customerRepository) {
    this.dataSource = dataSource;
    this.jdbc = jdbc;
    this.customerRepository = customerRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    try (var con = dataSource.getConnection();
         var rs = con.getMetaData().getTables(null,
                 null,
                 "%",
                 null)) {
      while (rs.next()) {
        logger.info("{}", rs.getString(3));
      }
    }

    System.out.println("###### DB content via DataSource ######");
    var query = "SELECT id, name, email FROM customer";
    try (var con = dataSource.getConnection();
         var stmt = con.createStatement();
         var rs = stmt.executeQuery(query)) {
      while (rs.next()) {
        logger.info("Customer [id={}, name={}, email={}]",
                rs.getLong(1), rs.getString(2), rs.getString(3));
      }
    }

    System.out.println("###### DB content via JdbcTemplate ######");
    jdbc.query(query, rs -> {
      logger.info("Customer [id={}, name={}, email={}]",
              rs.getLong(1), rs.getString(2), rs.getString(3));
    });

    System.out.println("###### DB content via Jdbc repository ######");
    customerRepository.findAll()
            .forEach(customer -> logger.info("{}", customer));

  }
}

@Component
class CustomerLister implements ApplicationRunner {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final DataSource dataSource;

  CustomerLister(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    try (var con = dataSource.getConnection();
         var stmt = con.createStatement();
         var rs = stmt.executeQuery("SELECT id, name, email FROM customer")
    ) {
      while (rs.next()) {
        logger.info("Customer [id={}, name={}, email={}]",
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3));
      }
    }
  }
}
