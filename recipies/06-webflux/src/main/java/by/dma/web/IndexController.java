package by.dma.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Thymeleaf index Views.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
@Controller
public class IndexController {

  @GetMapping
  public String index() {
    return "index";
  }
}
