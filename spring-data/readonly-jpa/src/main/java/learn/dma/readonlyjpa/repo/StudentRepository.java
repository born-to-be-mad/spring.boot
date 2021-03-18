package learn.dma.readonlyjpa.repo;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

import learn.dma.readonlyjpa.domain.Student;

/**
 * DataSource Management for the Students at the University.
 */
public interface StudentRepository extends CrudRepository<Student, Integer> {
}
