package by.dma1979.jdbc;

import java.util.List;

/**
 * @author : Dzmitry Marudau
 * @created at : 15:53
 * @since : 2019.12
 **/public interface CustomerRepository {
    List<Customer> findAll();
    Customer findById(long id);
    Customer save(Customer customer);
}

public interface CustomerRepository {
    List<Customer> findAll();

    Customer findById(long id);

    Customer save(Customer customer);
}
