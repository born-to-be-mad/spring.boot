package by.dma1979.order.web;

import by.dma1979.order.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:35
 * @since : 2019.11
 **/
@Controller
@RequestMapping("/orders")
class OrderController {
    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Mono<String> list(Model model) {
        var orders = orderService.orders();
        model.addAttribute("orders", new ReactiveDataDriverContextVariable(orders, 5));
        return Mono.just("orders/list");
    }
}
