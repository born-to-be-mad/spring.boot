package by.dma1979.jpa.spring;

import by.dma1979.jpa.entity.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:23
 * @since : 2019.12
 **/
public interface CustomerRepository extends CrudRepository<Customer, Long> { }

