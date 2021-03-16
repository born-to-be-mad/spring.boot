package by.dma.demo.persist;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import by.dma.demo.model.Mentee;

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
}
