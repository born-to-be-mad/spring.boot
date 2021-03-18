package learn.dma.readonlyjpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import learn.dma.readonlyjpa.domain.Course;
import learn.dma.readonlyjpa.repo.CourseQueryRepository;
import learn.dma.readonlyjpa.view.CourseView;

@DataJpaTest
public class CourseQueryRepositoryTest {

    @Autowired
    private CourseQueryRepository courseRepository;

    /**
     * Common Mistakes, Uncomment to debug
     * <p>
     * Courses persisted to H2 in-Memory database at startup.
     *
     * @see ReadonlyJpaApplication
     */
    @Test
    public void runtimeErrors() {

        Course course = courseRepository.findByDepartmentName("Sciences");

        //Various ways to leverage the Optional
        CourseView view = courseRepository.getCourseViewByName("English 101").get();
        view = courseRepository.getCourseViewByName("English 101").orElseThrow();
        view = courseRepository.getCourseViewByName("English 100").orElse(
            new CourseView("dummyCourse",
                           "Bad Instructor",
                           "No Department"));
    }


}
