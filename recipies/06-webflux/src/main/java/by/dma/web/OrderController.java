package by.dma.web;

import by.dma.service.OrderService;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

/**
 *  Uses Thymeleaf as template engine for views. Adds Flux<Order> to the model.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
@Controller
@RequestMapping("/web-orders")
class OrderController {
  private final OrderService orderService;

  OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public Mono<String> list(Model model) {
    var orders = orderService.orders();
    //model.addAttribute("orders", orders);

    // The Flux is wrapped in the ReactiveDataDriverContextVariable.
    // It will start rendering as soon as five elements are received
    // and will continue to render the page until everything is received
    model.addAttribute("orders", new ReactiveDataDriverContextVariable(orders, 5));
    return Mono.just("orders/list");
  }
}
