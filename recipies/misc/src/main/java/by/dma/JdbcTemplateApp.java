package by.dma;

import javax.sql.DataSource;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.util.FileCopyUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

/**
 * A lightweight SQL data mapper with the JdbcTemplate.
 *
 * @author dzmitry.marudau
 * @since 2021.1
 */
@SpringBootApplication
public class JdbcTemplateApp {
  public static void main(String[] args) {
    SpringApplication.run(JdbcTemplateApp.class, args);
  }

  @Bean
  DataSource dataSource() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
  }

  @SneakyThrows
  private String loadSql() {
    Resource resource = new ClassPathResource("/initialization.sql");
    try (Reader reader = new InputStreamReader(resource.getInputStream())) {
      return FileCopyUtils.copyToString(reader);
    }
  }

  @Bean
  ApplicationListener<ApplicationReadyEvent> ready(DataSource dataSource) {
    return event -> {
      String sql = loadSql();
      String[] names = new String[] {
          "Clemens", "Sabine", "Stef", "Jurgen", "Dzmitry", "Kyril", "Serg", "Kim", "Franz-Martin"
      };
      JdbcTemplate template = new JdbcTemplate(dataSource);
      template.execute(sql);
      for (var name : names) {
        template.update("insert into CUSTOMER(name) values(?)", name);
      }
      List<Customer> results = template.query(
          "select * from CUSTOMER",
          (resultSet, i) -> new Customer(resultSet.getInt("id"),
                                         resultSet.getString("name")));
      results.forEach(System.out::println);
    };
  }

  @AllArgsConstructor
  @Data
  private class Customer {
    final int id;
    final String name;
  }
}
