package learn.dma.readonlyjpa.repo;

import org.springframework.data.repository.CrudRepository;

import learn.dma.readonlyjpa.domain.Course;

/**
 * DataSource Management for the Courses at the University.
 *
 */
public interface CourseRepository extends CrudRepository<Course,Integer>{

}
