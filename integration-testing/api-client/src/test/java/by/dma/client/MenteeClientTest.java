package by.dma.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.BDDAssertions.then;

import by.dma.client.dto.Mentee;

@SpringBootTest
@AutoConfigureWireMock(port = 8080)
public class MenteeClientTest {

    @Autowired
    private MenteeClient client;

    @Test
    public void shouldReturnExistingMentee() {
        // given:
        long givenId = 123L;
        stubFor(get("/mentees/"+givenId).willReturn(okJson(
            "{\"id\":1, \"name\": \"Dzmitry\", \"grade\": 90}")));

        // when:
        Mentee mentee = client.getMentee(givenId);

        // then:
        then(mentee.getId()).isNotNull();
        then(mentee.getName()).isEqualTo("Dzmitry");
        then(mentee.getGrade()).isEqualTo(90);
    }
}
