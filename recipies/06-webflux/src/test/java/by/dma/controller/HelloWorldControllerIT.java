package by.dma.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:05
 * @since : 2020.06
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldControllerIT {
  @Autowired
  private WebTestClient webClient;

  @Test
  public void shouldSayHello() {
    webClient.get().uri("/hello").accept(MediaType.TEXT_PLAIN)
             .exchange()
             .expectStatus().isOk()
             .expectBody(String.class)
             .isEqualTo("Hello World, from Reactive Spring Boot!");
  }
}
