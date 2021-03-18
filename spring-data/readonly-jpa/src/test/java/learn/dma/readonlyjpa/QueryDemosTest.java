package learn.dma.readonlyjpa;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import learn.dma.readonlyjpa.domain.Course;
import learn.dma.readonlyjpa.domain.Department;
import learn.dma.readonlyjpa.domain.Person;
import learn.dma.readonlyjpa.domain.Staff;
import learn.dma.readonlyjpa.repo.CourseQueryRepository;
import learn.dma.readonlyjpa.repo.DepartmentRepository;
import learn.dma.readonlyjpa.repo.StaffRepository;
import learn.dma.readonlyjpa.repo.StudentQueryRepository;

/**
 * Demonstrate Various Querying Techniques with Spring Data Commons
 * <p>
 * Created by maryellenbowman
 */

@DataJpaTest
public class QueryDemosTest {
    @Autowired
    StudentQueryRepository studentRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    CourseQueryRepository courseRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    /**
     * Simple Property Expression Queries.
     * <p>
     * Students persisted to H2 in-Memory database at startup.
     *
     * @see ReadonlyJpaApplication
     */
    @Test
    public void simpleQueryExamples() {

        System.out.println("\nFind 20 year old students");
        studentRepository.findByAge(20).forEach(System.out::println);
        System.out.println("\nFind full time old students");
        studentRepository.findByFullTime(true).forEach(System.out::println);
        System.out.println("\nFind students with 'doe' as last name");
        studentRepository.findByAttendeeLastName("doe").forEach(System.out::println);
    }

    /**
     * Advanced Property Expression Queries
     * <p>
     * Students persisted to H2 in-Memory database at startup.
     *
     * @see ReadonlyJpaApplication
     */
    @Test
    public void intermediateQueryExamples() {
        System.out.println("Find students by name and traverse entities \n" +
                           studentRepository.findByAttendeeFirstNameAndAttendeeLastName("jane", "doe"));
        System.out.println("Find students by name with Person Object \n" +
                           studentRepository.findByAttendee(new Person("jane", "doe")));

        System.out.println("\nFind Students older than 19");
        studentRepository.findByAgeGreaterThan(19).forEach(System.out::println);

        System.out.println("\nFind Students under 19");
        studentRepository.findByAgeLessThan(19).forEach(System.out::println);

        System.out.println("\nFind Students with last name Doe, despite the case");
        studentRepository.findByAttendeeLastNameIgnoreCase("Doe").forEach(System.out::println);

        System.out.println("\nFind Students with an i in the last name");
        studentRepository.findByAttendeeLastNameLike("%i%").forEach(System.out::println);

        System.out.println("\nFind first Student in alphabet \n" +
                           studentRepository.findFirstByOrderByAttendeeLastNameAsc());

        System.out.println("\nFind oldest Student \n" +
                           studentRepository.findTopByOrderByAgeDesc());

        System.out.println("\nFind 3 oldest Students \n" +
                           studentRepository.findTop3ByOrderByAgeDesc());

    }

    /**
     * @Query Queries
     * <p>
     * Courses persisted to H2 in-Memory database at startup.
     * @see ReadonlyJpaApplication
     */
    @Test
    public void jpqlQueries() {
        //*******Method Simplification*******

        System.out.println("Find Courses where Jones is the department Chair with Property Expression");
        courseRepository.findByDepartmentChairMemberLastName("Jones").forEach(System.out::println);

        //Select c from Course c where c.department.chair.member.lastName=:chair
        System.out.println("\nFind Courses where Jones is the department Chair with @Query");
        courseRepository.findByChairLastName("Jones").forEach(System.out::println);


        //*******Complex Queries********
        //Leverage Optional.ifPresent to avoid null checks
        courseRepository.findByName("English 101").ifPresent(english101 -> {
            //Select c from Course c join c.prerequisites p where p.id = ?1
            System.out.println("\nFind Courses where English 101 is a prerequisite");
            courseRepository.findCourseByPrerequisite(english101.getId())
                            .forEach(System.out::println);

            //Select new com.example.university.view.CourseView
            //  (c.name, c.instructor.member.lastName, c.department.name) from Course c where c.id=?1
            System.out.println("\nCourseView for English 101 \n" +
                               courseRepository.getCourseView(english101.getId()));
        });
    }

    /**
     * Queries that use Paging and Sorting
     * <p>
     * Courses persisted to H2 in-Memory database at startup.
     *
     * @see ReadonlyJpaApplication
     */
    @Test
    public void pagingAndSortingQueries() {
        System.out.println("\nFind all 3-credit courses");
        courseRepository.findByCredits(3).forEach(System.out::println);

        System.out.println("\nFind first 4 3-credit courses, sort by credit, then course name");
        Page<Course> courses = courseRepository
            .findByCredits(3,
                           PageRequest.of(0, 4, Sort.Direction.ASC, "credits", "name"));
        courses.forEach(System.out::println);

        System.out.println("\nFind all staff members, sort alphabetically by last name");
        Sort sortByLastName = Sort.by(Sort.Direction.ASC, "member.lastName");
        staffRepository.findAll(sortByLastName).forEach(System.out::println);

        Page<Staff> members = staffRepository.findAll(PageRequest.of(0, 5, sortByLastName));
        System.out.println("\nTotal number of staff members=" + members.getTotalElements());
        System.out.println("Total number of 5-element-pages=" + members.getTotalPages());
        System.out.println("Find first 5 Staff members, sort alphabetically by last name");
        members.forEach(System.out::println);
    }

    /**
     * Queries using Query by Example
     * <p>
     * Departments persisted to H2 in-Memory database at startup.
     *
     * @see ReadonlyJpaApplication
     */
    @Test
    public void queryByExample() {
        System.out.println("\nFind the Department with the name 'Humanities' \n" +
                           departmentRepository.findOne(Example.of(new Department("Humanities", null))));


        System.out.println("\nFind Departments with the first name of the chair is 'John'");
        departmentRepository.findAll(Example.of(
            new Department(null, new Staff(new Person("John", null)))))
                            .forEach(System.out::println);

        System.out.println("\nFind All Departments with the name ending in 'sciences', case insensitive");
        departmentRepository.findAll(
            Example.of(
                new Department("sciences", null),
                ExampleMatcher.matching()
                              .withIgnoreCase()
                              .withStringMatcher(ExampleMatcher.StringMatcher.ENDING)))
                            .forEach(System.out::println);

    }


    @BeforeEach
    @AfterEach
    public void printBanner() {
        System.out.println("*".repeat(30));
    }

}
