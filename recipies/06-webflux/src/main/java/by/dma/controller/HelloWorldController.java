package by.dma.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:48
 * @since : 2020.06
 **/
@RestController
public class HelloWorldController {
  @GetMapping("/hello")
  public Mono<String> hello() {
    return Mono.just("Hello World, from Reactive Spring Boot!");
  }
}

