package learn.dma.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import learn.dma.jpa.domain.Person;
import learn.dma.jpa.domain.Student;

/**
 * DataSource Management for the Students at the University.
 */
public interface StudentRepository extends CrudRepository<Student, Integer> {
    //Simple Query Methods
    List<Student> findByFullTime(boolean fullTime);

    List<Student> findByAge(Integer age);

    List<Student> findByAttendeeLastName(String lastName);

    //Query Methods with Conditional Expressions(Clauses and Expressions)
    Student findByAttendeeFirstNameAndAttendeeLastName(String firstName, String lastName);

    Student findByAttendee(Person person);

    List<Student> findByAgeGreaterThan(int minimumAge);

    List<Student> findByAgeLessThan(int maximumAge);

    List<Student> findByAttendeeLastNameIgnoreCase(String lastName);

    // Wildcard search
    List<Student> findByAttendeeLastNameLike(String likeString);

    // Find highest student in the alphabet
    Student findFirstByOrderByAttendeeLastNameAsc();

    //Find the oldest student
    Student findTopByOrderByAgeDesc();

    //Find e oldest students
    List<Student> findTop3ByOrderByAgeDesc();

    List<Student> findByFullTimeOrAgeLessThan(boolean fullTime, int maximumAge);

    // NATIVE
    @Query(value = "SELECT * FROM student s ORDER BY s.student_age LIMIT 3", nativeQuery = true)
    List<Student> findThreeYoungestStudents();
}
