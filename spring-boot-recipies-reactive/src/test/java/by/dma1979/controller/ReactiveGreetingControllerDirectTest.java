package by.dma1979.controller;

import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.hamcrest.CoreMatchers.containsString;

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
            .expectNextMatches(messsage -> messsage.contains("Chuck Norris"))
            .verifyComplete();
    }
}