package by.dma1979.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;


/**
 * @author : Dzmitry Marudau
 * @created at : 20:19
 * @since : 2019.11
 **/
@RunWith(SpringRunner.class)
@WebFluxTest(GreetingRestController.class)
public class ReactiveGreetingControllerWebFluxTest {
    @Autowired
    private WebTestClient webClient;

    @MockBean(name = "calculationRunner")
    private ApplicationRunner calculator;

    @MockBean(name = "booksInitializer")
    private ApplicationRunner booksInitializer;

    @Test
    public void shouldSayReactiveHello() {
        webClient.get().uri("/helloreactive").accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Hello World, from Reactive Spring Boot 2!");
    }
}
