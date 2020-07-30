package by.dma.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import by.dma.entity.Order;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:54
 * @since : 2020.07
 **/
@Component
public class OrderListener {

  @JmsListener(destination = "orders")
  public void handle(Order order) {
    System.out.println("[RECEIVED] from 'orders' queue: " + order);
  }
}
