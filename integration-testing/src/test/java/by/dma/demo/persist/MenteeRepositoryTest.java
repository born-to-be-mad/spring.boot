package by.dma.demo.persist;

import java.util.Arrays;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import by.dma.demo.model.Mentee;

import static org.assertj.core.api.BDDAssertions.then;

//@SpringBootTest
@DataJpaTest
public class MenteeRepositoryTest {

    @Autowired
    private MenteeRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void shouldFindMenteeByName_whenItExists() {
        // given:
        // Setup the system under test
        //Mentee savedMentee = repository.save(new Mentee(null, "Dima"));
        Mentee savedMentee = entityManager.persistFlushFind(new Mentee(null, "Dima"));

        // when:
        // Execute the system under test
        Mentee mentee = repository.getMenteeByName("Dima");

        // then:
        // Assert that the expected change has occurred
        BDDAssertions.then(savedMentee.getId()).isNotNull();
        BDDAssertions.then(savedMentee.getName()).isEqualTo(mentee.getName());
    }

    @Test
    public void shouldCalculateAvgActiveMenteeRates_whenDifferentMenteesPassed() {
        // given:
        // Setup the system under test
        Mentee konstantin = Mentee.builder().name("Konstantin").active(true).grade(80).build();
        Mentee dzmitry = Mentee.builder().name("Dzmitry").active(true).grade(90).build();
        Mentee ivan = Mentee.builder().name("Ivan").active(false).grade(100).build();
        Mentee clemens = Mentee.builder().name("Clemens").active(true).grade(100).build();
        Mentee stefan = Mentee.builder().name("Stefan").active(false).grade(90).build();
        Arrays.asList(konstantin, dzmitry, ivan, clemens, stefan)
              .forEach(entityManager::persistFlushFind);

        // when:
        // Execute the system under test
        Double avgGrade = repository.getAvgGradeForActiveMentees();

        // then:
        // Assert that the expected change has occurred
        then(avgGrade).isEqualTo(90);
    }
}
