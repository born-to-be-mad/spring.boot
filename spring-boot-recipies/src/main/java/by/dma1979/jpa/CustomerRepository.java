package by.dma1979.jpa;

import java.util.List;

public interface CustomerRepository {

    List<Customer> findAll();

    Customer findById(long id);

    Customer save(Customer customer);

    void delete(Customer customer);
}
