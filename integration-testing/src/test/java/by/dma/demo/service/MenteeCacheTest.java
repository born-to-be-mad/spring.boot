package by.dma.demo.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import by.dma.demo.model.Mentee;
import by.dma.demo.persist.MenteeRepository;
import by.dma.demo.web.service.MenteeService;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class MenteeCacheTest {

    @Autowired
    private MenteeService service;

    @MockBean
    private MenteeRepository repository;

    @Test
    public void shouldCallServiceOnlyOnce_whenMultipleCallsApplied() {
        // given:
        long givenId = 123L;
        given(repository.findById(givenId))
            .willReturn(Optional.of(Mentee.builder().name("Dzmitry").build()));

        // when:
        // Execute the system under test
        service.getMenteeById(givenId);
        service.getMenteeById(givenId);
        service.getMenteeById(givenId);
        service.getMenteeById(givenId);

        // then:
        // Assert that the expected change has occurred
        then(repository).should(times(1)).findById(givenId);
    }
}
