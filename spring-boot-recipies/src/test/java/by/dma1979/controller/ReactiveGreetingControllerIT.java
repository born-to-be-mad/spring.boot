package by.dma1979.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author : Dzmitry Marudau
 * @created at : 21:23
 * @since : 2019.11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/*@AutoConfigureWebClient*/
public class ReactiveGreetingControllerIT {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void shouldSayReactiveHello() {
        webClient.get().uri("/helloreactive").accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Hello World, from Reactive Spring Boot 2!");
    }
}
