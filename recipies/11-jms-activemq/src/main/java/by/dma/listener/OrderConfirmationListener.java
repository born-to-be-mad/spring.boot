package by.dma.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import by.dma.entity.OrderConfirmation;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:09
 * @since : 2020.07
 **/
@Component
public class OrderConfirmationListener {
  @JmsListener(destination = "order-confirmations")
  public void handle(OrderConfirmation confirmation) {
    System.out.println("[RECEIVED] from 'order-confirmations': " + confirmation);
  }
}
