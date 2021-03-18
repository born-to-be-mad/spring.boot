package learn.dma.datarest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.dma.datarest.domain.Department;

/**
 * DataSource Management for the Departments at the University.
 *
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
