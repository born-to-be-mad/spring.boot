package by.dma.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:30
 * @since : 2020.05
 **/
@RestController
public class HelloWorldController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello World from Spring Boot!";
  }
}
