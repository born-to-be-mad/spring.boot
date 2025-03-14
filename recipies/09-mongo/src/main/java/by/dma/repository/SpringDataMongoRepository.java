package by.dma.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import by.dma.entity.Customer;

/**
 * Use Spring Data JPA: we can use MongoRepository or CrudRepository.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public interface SpringDataMongoRepository extends MongoRepository<Customer, String> {
}
