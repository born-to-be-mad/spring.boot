package by.dma1979.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:16
 * @since : 2019.11
 **/

public class GreetingReactiveControllerTest {

    private final GreetingController controller = new GreetingController();

    @Test
    public void shouldSayHello() {
        Mono<String> result = controller.helloReactive();
        StepVerifier.create(result)
                .expectNext("Hello World, from Reactive Spring Boot 2!")
                .verifyComplete();
    }
}