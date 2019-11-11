package by.dma1979.order.web;

import by.dma1979.order.Order;
import by.dma1979.order.OrderService;
import org.springframework.http.MediaType;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Order> store(@RequestBody Mono<Order> order) {
        return orderService.save(order);
    }

    @GetMapping("/{id}")
    public Mono<Order> find(@PathVariable("id") String id) {
        return orderService.findById(id);
    }

    //header will be application/json
    @GetMapping
    public Flux<Order> list() {
        return orderService.orders();
    }

    // header will be application/stream+json
    @GetMapping(value = "/stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Order> listStream() {
        return orderService.orders();
    }

    @GetMapping(value = "/event-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Order> listEventStream() {
        return orderService.orders();
    }

}
