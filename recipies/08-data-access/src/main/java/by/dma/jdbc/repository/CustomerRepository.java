package by.dma.jdbc.repository;

import java.util.List;

import by.dma.jdbc.entity.Customer;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:23
 * @since : 2020.07
 **/
public interface CustomerRepository {
  List<Customer> findAll();

  Customer findById(long id);

  Customer save(Customer customer);
}
