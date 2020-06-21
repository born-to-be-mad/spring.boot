package by.dma.controller;

import by.dma.model.Order;
import by.dma.service.OrderService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes the {@link Order} as a REST resource.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;

  OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public Flux<Order> list() {
    return orderService.orders();
  }

  @GetMapping(value = "/as-event", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Order> listAsEvent() {
    return orderService.orders();
  }

  @GetMapping("/{id}")
  public Mono<Order> find(@PathVariable("id") String id) {
    return orderService.findById(id);
  }

  @PostMapping
  public Mono<Order> store(@RequestBody Mono<Order> order) {
    return orderService.save(order);
  }
}
