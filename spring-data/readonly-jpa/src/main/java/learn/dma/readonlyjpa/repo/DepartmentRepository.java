package learn.dma.readonlyjpa.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import learn.dma.readonlyjpa.domain.Department;

/**
 * DataSource Management for the Departments at the University.
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
