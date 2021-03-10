package by.dma.securityjpa.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeResource {

  @GetMapping("/")
  public String home() {
    return ("<h1>Welcome to Spring Boot!</h1>");
  }

  @GetMapping("/user")
  public String user() {
    return ("<h1>Welcome to Personal View</h1>");
  }

  @GetMapping("/admin")
  public String admin() {
    return ("<h1>Welcome to Admin Panel</h1>");
  }
}
