package by.dma.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
@Controller
public class IndexContoller {
  @GetMapping
  public String index() {
    return "index";
  }
}
