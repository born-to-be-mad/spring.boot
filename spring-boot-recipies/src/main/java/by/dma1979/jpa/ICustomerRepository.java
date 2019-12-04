package by.dma1979.jpa;

import by.dma1979.jpa.entity.Customer;

import java.util.List;

public interface ICustomerRepository {

    List<Customer> findAll();

    Customer findById(long id);

    Customer save(Customer customer);

    void delete(Customer customer);
}
