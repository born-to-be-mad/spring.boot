package by.dma.controller;

import by.dma.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;

/**
 * @author : Dzmitry Marudau
 * @created at : 09:35
 * @since : 2020.05
 **/
@RestController
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/orders")
  public ResponseBodyEmitter ordersViaResponseBodyEmitter() {
    var emitter = new ResponseBodyEmitter();
    var executor = Executors.newSingleThreadExecutor();
    executor.execute(() -> {
      var orders = orderService.findAll();
      try {
        for (var order : orders) {
          randomDelay();
          emitter.send(order);
        }
        emitter.complete();
      } catch (IOException e) {
        emitter.completeWithError(e);
      }
    });
    executor.shutdown();
    return emitter;
  }

  @GetMapping("/sse-orders")
  public SseEmitter ordersViaSseEmitter() {
    SseEmitter emitter = new SseEmitter();
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.execute(() -> {
      var orders = orderService.findA`ll();
      try {
        for (var order : orders) {
          randomDelay();
          var eventBuilder = event();
          emitter.send(
                  eventBuilder
                          .data(order)
                          .name("order-created")
                          .id(String.valueOf(order.hashCode())));
        }
        emitter.complete();
      } catch (IOException e) {
        emitter.completeWithError(e);
      }
    });
    executor.shutdown();
    return emitter;
  }

  private void randomDelay() {
    try {
      Thread.sleep(ThreadLocalRandom.current()
                                    .nextInt(150));
    } catch (InterruptedException e) {
      Thread.currentThread()
            .interrupt();
    }
  }
}
