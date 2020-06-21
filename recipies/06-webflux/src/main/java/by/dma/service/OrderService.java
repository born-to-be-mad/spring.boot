package by.dma.service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import by.dma.model.Order;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:17
 * @since : 2020.06
 **/
@Service
public class OrderService {
  private final Map<String, Order> orders = new ConcurrentHashMap<>(25);

  @PostConstruct
  public void init() {
    OrderGenerator generator = new OrderGenerator();
    for (int i = 0; i < 25; i++) {
      var order = generator.generate();
      orders.put(order.getId(), order);
    }
  }

  public Mono<Order> findById(String id) {
    return Mono.justOrEmpty(orders.get(id));
  }

  public Mono<Order> save(Mono<Order> order) {
    return order.map(this::save);
  }

  private Order save(Order order) {
    orders.put(order.getId(), order);
    return order;
  }

  public Flux<Order> orders() {
    return Flux.fromIterable(orders.values())
               .delayElements(Duration.ofMillis(128L + (long) (128L * Math.random())));
  }
}
