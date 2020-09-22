package by.dma.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import by.dma.entity.Customer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link SpringDataMongoRepository}.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class SpringDataMongoRepositoryTest {

  @Autowired
  private SpringDataMongoRepository repository;

  @After
  public void cleanUp() {
    repository.deleteAll();
  }

  @Test
  public void insertNewCustomer() {
    assertThat(repository.findAll()).isEmpty();
    Customer customer = repository.save(new Customer("T. Testing", "t.testing@test123.tst"));
    assertThat(customer.getName()).isEqualTo("T. Testing");
    assertThat(customer.getEmail()).isEqualTo("t.testing@test123.tst");
    assertThat(repository.findById(customer.getId()).get()).isEqualTo(customer);
  }

  @Test
  public void findAllCustomers() {
    assertThat(repository.findAll()).isEmpty();
    repository.save(new Customer("T. Testing1", "t.testing@test123.tst"));
    repository.save(new Customer("Dzmitry Marudau", "vinmaster@tut.by"));
    assertThat(repository.findAll()).hasSize(2);
  }
}