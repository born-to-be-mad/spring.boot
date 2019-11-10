package by.dma1979.order;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:28
 * @since : 2019.11
 **/
@Service
public class OrderService {
    public static final int AMOUNT_OF_ORDERS = 25;
    private final Map<String, Order> orders = new ConcurrentHashMap<>(10);

    @PostConstruct
    public void init() {
        OrderGenerator generator = new OrderGenerator();
        for (int i = 0; i < AMOUNT_OF_ORDERS; i++) {
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

    private Order save(Order newOrder) {
        Order previous = orders.put(newOrder.getId(), newOrder);
        return newOrder;
    }

    public Flux<Order> orders() {
        return Flux.fromIterable(orders.values())
                .delayElements(Duration.ofMillis(128L));
    }
}
