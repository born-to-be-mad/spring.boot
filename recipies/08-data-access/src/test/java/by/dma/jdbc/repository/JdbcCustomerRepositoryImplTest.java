package by.dma.jdbc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import by.dma.jdbc.entity.Customer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:38
 * @since : 2020.07
 **/
@RunWith(SpringRunner.class)
@JdbcTest(includeFilters =
@ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern = "by.dma.repository.*"))
@TestPropertySource(properties = "spring.flyway.enabled=false")
public class JdbcCustomerRepositoryImplTest {

  @Autowired
  JdbcCustomerRepositoryImpl repository;

  @Test
  public void insertNewCustomer() {
    assertThat(repository.findAll()).isEmpty();
    Customer customer = repository.save(new Customer(-1, "T. Testing",
            "t.testing@test123.tst"));
    assertThat(customer.getId()).isGreaterThan(-1L);
    assertThat(customer.getName()).isEqualTo("T. Testing");
    assertThat(customer.getEmail()).isEqualTo("t.testing@test123.tst");
    assertThat(repository.findById(customer.getId())).isEqualTo(customer);
  }

  @Test
  public void findAllCustomers() {
    assertThat(repository.findAll()).isEmpty();
    repository.save(new Customer(-1, "T. Testing1", "t.testing@test123.tst"));
    repository.save(new Customer(-1, "T. Testing2", "t.testing@test123.tst"));
    assertThat(repository.findAll()).hasSize(2);
  }
}
