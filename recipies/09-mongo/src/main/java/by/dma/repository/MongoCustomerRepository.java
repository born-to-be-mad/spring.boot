package by.dma.repository;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import by.dma.entity.Customer;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:00
 * @since : 2020.07
 **/
@Repository
public class MongoCustomerRepository implements ICustomerRepository {

  private final MongoTemplate mongoTemplate;

  MongoCustomerRepository(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public List<Customer> findAll() {
    return mongoTemplate.findAll(Customer.class);
  }

  @Override
  public Customer findById(long id) {
    return mongoTemplate.findById(id, Customer.class);
  }

  @Override
  public Customer save(Customer customer) {
    mongoTemplate.save(customer);
    return customer;
  }

  @Override
  public void delete(Customer customer) {
    mongoTemplate.remove(customer);
  }
}
