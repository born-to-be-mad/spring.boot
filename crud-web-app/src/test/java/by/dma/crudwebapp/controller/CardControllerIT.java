package by.dma.crudwebapp.controller;

import by.dma.crudwebapp.CrudWebAppApplication;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CrudWebAppApplication.class)
class CardControllerIT {

    private static final String API_CARDS = "/api/cards/";
    private static final String HTTP_BASE_URL = "http://localhost:";

    @LocalServerPort
    int randomServerPort;

    private TestRestTemplate testRestTemplate; //it is used to make http-calls

    @BeforeEach
    void setUp() {
        this.testRestTemplate = new TestRestTemplate();
    }

    @Test
    void getAll() {
        String baseUrl = getBaseUrl();

        ResponseEntity<JsonNode> response =
                testRestTemplate.getForEntity(baseUrl + API_CARDS, JsonNode.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertTrue(Objects.requireNonNull(response.getBody()).size() >= 10);

    }

    @Test
    void deletingKnownCardShouldNotBeFoundAfterDeletion() {
        long entityId = 1;
        String baseUrl = getBaseUrl();

        ResponseEntity<JsonNode> initialResponse =
                testRestTemplate.getForEntity(baseUrl + API_CARDS + entityId, JsonNode.class);

        assertThat(initialResponse.getStatusCode(), is(HttpStatus.OK));

        testRestTemplate.delete(baseUrl + API_CARDS + entityId);

        ResponseEntity<JsonNode> updatedResponse =
                testRestTemplate.getForEntity(baseUrl + API_CARDS + entityId, JsonNode.class);

        assertThat(updatedResponse.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    private String getBaseUrl() {
        return HTTP_BASE_URL + randomServerPort;
    }
}