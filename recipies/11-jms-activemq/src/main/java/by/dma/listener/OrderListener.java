package by.dma.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import by.dma.entity.Order;
import by.dma.entity.OrderConfirmation;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:54
 * @since : 2020.07
 **/
@Component
public class OrderListener {

  @JmsListener(destination = "orders")
  @SendTo("order-confirmations")
  public OrderConfirmation handle(Order order) {
    System.out.println("[RECEIVED] from 'orders' queue: " + order);
    return new OrderConfirmation(order.getId());
  }
}
