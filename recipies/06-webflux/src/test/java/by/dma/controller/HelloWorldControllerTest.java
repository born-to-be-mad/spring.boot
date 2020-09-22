package by.dma.controller;

import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:52
 * @since : 2020.06
 **/
public class HelloWorldControllerTest {
  private final HelloWorldController controller = new
          HelloWorldController();
  @Test
  public void shouldSayHello() {
    Mono<String> result = controller.hello();
    StepVerifier.create(result)
                .expectNext("Hello World, from Reactive Spring Boot!")
                .verifyComplete();
  }
}
