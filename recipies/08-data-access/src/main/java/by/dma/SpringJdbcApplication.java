package by.dma;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:24
 * @since : 2020.05
 **/
@SpringBootApplication
public class SpringJdbcApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(
            SpringJdbcApplication.class, args);
  }
}

  @Component
  class TableLister implements ApplicationRunner {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final DataSource dataSource;
    TableLister(DataSource dataSource) {
      this.dataSource = dataSource;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
      try (var con = dataSource.getConnection();
           var rs = con.getMetaData().getTables(null, null, "%", null)) {
        while (rs.next()) {
          logger.info("{}", rs.getString(3));
        }
      }
    }
}
