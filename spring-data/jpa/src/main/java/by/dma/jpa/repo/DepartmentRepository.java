package by.dma.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import by.dma.jpa.domain.Department;

/**
 * DataSource Management for the Departments at the University.
 *
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
