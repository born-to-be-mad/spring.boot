package by.dma.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:38
 * @since : 2020.05
 **/
@RestController
public class CallableController {

  @GetMapping("/callable")
  public Callable<String> hello() {
    return () -> {
      Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
      return "Hello World, from Callable!";
    };
  }
}
