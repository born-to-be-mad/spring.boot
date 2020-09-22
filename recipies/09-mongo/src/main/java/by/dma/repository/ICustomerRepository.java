package by.dma.repository;

import java.util.List;

import by.dma.entity.Customer;

public interface ICustomerRepository {

  List<Customer> findAll();

  Customer findById(long id);

  Customer save(Customer customer);

  void delete(Customer customer);
}
