package learn.dma.datarest.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import learn.dma.datarest.domain.Course;
import learn.dma.datarest.view.CourseView;

/**
 * DataSource Management for the Courses at the University.
 *
 */
public interface CourseRepository extends CrudRepository<Course,Integer>{

    Optional<Course> findByName(String name);

    List<Course> findByDepartmentChairMemberLastName(String chair);

    @Query("Select c from Course c where c.department.chair.member.lastName=:chair")
    List<Course> findByChairLastName(@Param("chair")String chairLastName);

    @Query("Select c from Course c join c.prerequisites p where p.id = ?1")
    List<Course> findCourseByPrerequisite(int id);

    // we can't use reserved words in HQL like 'in', 'by' and etc
    @Query("Select new learn.dma.datarest.view.CourseView(c.name, c.instructor.member.lastName, c.department.name) " +
           "from Course c where c.id=?1")
    CourseView getCourseView(int courseId) ;

    List<Course> findByCredits(@Param("credits") int credits);

    Page<Course> findByCredits(@Param("credits") int credits, Pageable pageable);

    Course findByDepartmentName(String deptName);

    // we can't use reserved words in HQL like 'in', 'by' and etc
    @Query("Select new learn.dma.datarest.view.CourseView(c.name, c.instructor.member.lastName, c.department.name) "
           + "from Course c where c.name=?1")
    Optional<CourseView> getCourseViewByName(String name);

}
