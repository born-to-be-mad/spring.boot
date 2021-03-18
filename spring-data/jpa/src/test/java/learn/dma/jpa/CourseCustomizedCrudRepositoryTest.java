package learn.dma.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import learn.dma.jpa.domain.Course;
import learn.dma.jpa.repo.CourseRepository;
import learn.dma.jpa.view.CourseView;

@DataJpaTest
public class CourseCustomizedCrudRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Common Mistakes, Uncomment to debug
     * <p>
     * Courses persisted to H2 in-Memory database at startup.
     *
     * @see JpaApplication
     */
    @Test
    public void runtimeErrors(@Autowired ConfigurableEnvironment env) {
        env.getPropertySources().addFirst(new SimpleCommandLinePropertySource("demo"));
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
