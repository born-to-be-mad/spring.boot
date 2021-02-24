package by.dma.securityjwt.web;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple User Resource.
 *
 * @author dzmitry.marudau
 * @since 2021.1
 */
@RestController
public class UserResource {

  @GetMapping("/hello")
  public String hello() {
    return ThreadLocalRandom.current().nextBoolean() ? "hello" : "hi";
  }
}
