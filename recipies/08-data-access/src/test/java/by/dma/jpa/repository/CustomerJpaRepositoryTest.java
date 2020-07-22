package by.dma.jpa.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import by.dma.jpa.entity.Customer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:35
 * @since : 2020.07
 **/
@RunWith(SpringRunner.class)
@DataJpaTest // The @DataJpaTest will replace the preconfigured DataSource with an embedded one
@TestPropertySource(properties = "spring.flyway.enabled=false")
public class CustomerJpaRepositoryTest {
  @Autowired
  private SpringDataCustomerRepository repository;

  @Autowired
  private TestEntityManager testEntityManager;


  @Test
  public void insertNewCustomer() {
    assertThat(repository.findAll()).isEmpty();
    ;

    Customer customer = repository.save(
        new Customer("Dzmitry Alexandrov", "support@tut.by")
    );

    assertThat(customer.getId()).isGreaterThan(-1L);
    assertThat(customer.getName()).isEqualTo("Dzmitry Alexandrov");
    assertThat(customer.getEmail()).isEqualTo("support@tut.by");

    assertThat(repository.findById(customer.getId())).hasValue(customer);
  }

  @Test
  public void findAllCustomers() {
    assertThat(repository.findAll()).isEmpty();

    testEntityManager.persistAndFlush(
        new Customer("Dzmitry Alexandrov", "support@tut.by"));
    testEntityManager.persistAndFlush(
        new Customer("Iva Alexandrov", "support@tut.by"));

    assertThat(repository.findAll()).hasSize(2);
  }
}
