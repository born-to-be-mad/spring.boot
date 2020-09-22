package by.dma.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import by.dma.jpa.entity.Customer;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:32
 * @since : 2020.07
 **/
public interface SpringDataCustomerRepository extends CrudRepository<Customer, Long> {
}
