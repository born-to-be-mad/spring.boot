package by.dma.repository;

import java.util.List;

import by.dma.entity.Customer;

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
