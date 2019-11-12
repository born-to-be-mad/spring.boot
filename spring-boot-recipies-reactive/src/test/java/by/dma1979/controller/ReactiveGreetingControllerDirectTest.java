package by.dma1979.controller;

import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:16
 * @since : 2019.11
 **/

public class ReactiveGreetingControllerDirectTest {

    private final GreetingRestController controller = new GreetingRestController();

    @Test
    public void shouldSayReactiveHello() {
        Mono<String> result = controller.helloReactive();
        StepVerifier.create(result, 5000L)
                .expectNext("Hello World, from Reactive Spring Boot 2!")
                .verifyComplete();
    }
}