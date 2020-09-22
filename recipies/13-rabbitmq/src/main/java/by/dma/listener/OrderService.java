package by.dma.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import by.dma.entity.Order;
import by.dma.entity.OrderConfirmation;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:43
 * @since : 2020.08
 **/
@Component
public class OrderService {

  @RabbitListener(bindings = @QueueBinding(
      exchange = @Exchange(name = "orders", type = ExchangeTypes.TOPIC),
      value = @Queue(name = "incoming-orders"),
      key = "new-order"

  ))
  @SendTo("orders/order-confirmation")
  public OrderConfirmation handle(Order order) {
    System.out.println("[RECEIVED] - " + order);
    return new OrderConfirmation(order.getId());
  }
}
