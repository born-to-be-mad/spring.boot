package by.dma.demo.service;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import by.dma.demo.model.Mentee;
import by.dma.demo.persist.MenteeRepository;
import by.dma.demo.web.service.MenteeService;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@DisplayName("MenteeService Integration Test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
public class MenteeServiceIT {
    @Autowired
    private MenteeRepository repository;

    @Autowired
    private MenteeService service;

    @DisplayName("Returns saved mentee from service layer")
    @Test
    public void getMenteeById_forSavedMentee_isReturned() {
        // given:
        // Setup the system under test
        Mentee savedMentee = repository.save(new Mentee(null, "Dzmitry"));

        // when:
        // Execute the system under test
        Mentee mentee = service.getMenteeById(savedMentee.getId());

        // then:
        // Assert that the expected change has occurred
        then(mentee.getId()).isNotNull();
        then(mentee.getName()).isEqualTo("Dzmitry");
    }
}
