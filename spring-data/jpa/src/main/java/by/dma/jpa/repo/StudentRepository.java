package by.dma.jpa.repo;

import org.springframework.data.repository.CrudRepository;

import by.dma.jpa.domain.Student;

/**
 * DataSource Management for the Students at the University.
 *
 */
public interface StudentRepository extends CrudRepository<Student, Integer>{

}
