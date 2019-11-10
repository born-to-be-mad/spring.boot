package by.dma1979.order.web;

import by.dma1979.order.Order;
import by.dma1979.order.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Exposes the Order as a REST resource.
 *
 * @author : Dzmitry Marudau
 * @created at : 00:32
 * @since : 2019.11
 **/
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Mono<Order> store(@RequestBody Mono<Order> order) {
        return orderService.save(order);
    }

    @GetMapping("/{id}")
    public Mono<Order> find(@PathVariable("id") String id) {
        return orderService.findById(id);
    }

    @GetMapping
    public Flux<Order> list() {
        return orderService.orders();
    }
}
