package by.dma.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:49
 * @since : 2020.05
 **/
@RestController
public class CompletableFutureController {

  private final TaskExecutor taskExecutor;

  public CompletableFutureController(TaskExecutor taskExecutor) {
    this.taskExecutor = taskExecutor;
  }

  @GetMapping("/completable-future")
  public CompletableFuture<String> hello() {
    return CompletableFuture.supplyAsync(() -> {
      randomDelay();
      return "Hello World, from CompletableFuture!";
    }, taskExecutor);
  }

  private void randomDelay() {
    try {
      Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
