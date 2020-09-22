package by.dma.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Access to Memory Parameters.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
@RestController
@RequestMapping("/system")
public class SystemController {

  @GetMapping("/free-memory")
  public Long freeMemory() {
    return Runtime.getRuntime()
                  .freeMemory();
  }

  @GetMapping("/total-memory")
  public Long totalMemory() {
    return Runtime.getRuntime()
                  .totalMemory();
  }

  @GetMapping("/max-memory")
  public Long maxMemory() {
    return Runtime.getRuntime()
                  .maxMemory();
  }
}
