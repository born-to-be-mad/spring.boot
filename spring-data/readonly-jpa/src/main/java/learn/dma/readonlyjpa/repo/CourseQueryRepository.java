package learn.dma.readonlyjpa.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import learn.dma.readonlyjpa.domain.Course;
import learn.dma.readonlyjpa.view.CourseView;

/**
 * Declaring a CourseQueryRepository which can only query the Database
 */
public interface CourseQueryRepository extends ReadOnlyRepository<Course, Integer> {
    Optional<Course> findByName(String name);

    List<Course> findByDepartmentChairMemberLastName(String chair);

    @Query("Select c from Course c where c.department.chair.member.lastName=:chair")
    List<Course> findByChairLastName(@Param("chair") String chairLastName);

    @Query("Select c from Course c join c.prerequisites p where p.id = ?1")
    List<Course> findCourseByPrerequisite(int id);

    @Query("Select new learn.dma.readonlyjpa.view.CourseView" +
           "(c.name, c.instructor.member.lastName, c.department.name) from Course c where c.id=?1")
    CourseView getCourseView(int courseId);

    List<Course> findByCredits(@Param("credits") int credits);

    Page<Course> findByCredits(@Param("credits") int credits, Pageable pageable);

    Course findByDepartmentName(String deptName);

    @Query("Select new learn.dma.readonlyjpa.view.CourseView" +
           "(c.name, c.instructor.member.lastName, c.department.name) from Course c where c.name=?1")
    Optional<CourseView> getCourseViewByName(String name);
}
