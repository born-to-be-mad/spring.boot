package by.dma.reactive.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import by.dma.reactive.domain.Staff;
import reactor.core.publisher.Flux;

/**
 * DataSource Management for the Staff at the University.
 * .
 */
public interface StaffRepository extends ReactiveCrudRepository<Staff, Integer> {
    Flux<Staff> findByMemberLastName(String lastName);
}
