package by.dma.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityPatternsController {
  @GetMapping("/hello")
  public String hello() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return "Hello, " + authentication.getName() + "!";
  }

  @GetMapping("/hi")
  public String hi(@CurrentSecurityContext(expression = "authentication.name") String name) {
    return "Hi, " + name + "!";
  }
}
