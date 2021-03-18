package by.dma.reactive.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import by.dma.reactive.domain.Department;

/**
 * DataSource Management for the Departments at the University.
 * .
 */
public interface DepartmentRepository extends ReactiveCrudRepository<Department, String> {

}
