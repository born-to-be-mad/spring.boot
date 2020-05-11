package by.dma1979.jpa.spring;

import by.dma1979.jdbc.CustomerRepository;
import by.dma1979.jpa.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:41
 * @since : 2019.12
 **/
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false")
public class SpringDataJpaCustomerRepositoryIT {

  @Autowired
  private SpringDataJpaCustomerRepository repository;

  @Autowired
  private TestEntityManager testEntityManager;

  @MockBean
  private CustomerRepository customerRepository;

  @MockBean(name = "calculationRunner")
  private ApplicationRunner calculator;

  @MockBean(name = "booksInitializer")
  private ApplicationRunner booksInitializer;

  @Test
  public void insertNewCustomer() {
    assertThat(repository.findAll()).isEmpty();;

    Customer customer = repository.save(new Customer("Dzmitry Alexandrov", "support@tut.by"));

    assertThat(customer.getId()).isGreaterThan(-1L);
    assertThat(customer.getName()).isEqualTo("Dzmitry Alexandrov");
    assertThat(customer.getEmail()).isEqualTo("support@tut.by");

    assertThat(repository.findById(customer.getId())).hasValue(customer);
  }

  @Test
  public void findAllCustomers() {
    assertThat(repository.findAll()).isEmpty();

    testEntityManager.persistAndFlush(new Customer("Dzmitry Alexandrov", "support@tut.by"));
    testEntityManager.persistAndFlush(new Customer("Iva Alexandrov", "support@tut.by"));

    assertThat(repository.findAll()).hasSize(2);
  }


}
