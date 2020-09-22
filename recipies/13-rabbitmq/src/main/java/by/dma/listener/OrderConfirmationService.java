package by.dma.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import by.dma.entity.OrderConfirmation;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:43
 * @since : 2020.08
 **/
@Component
public class OrderConfirmationService {

  @RabbitListener(bindings = @QueueBinding(
      exchange = @Exchange(name = "orders", type = ExchangeTypes.TOPIC),
      value = @Queue(name = "order-confirmations"),
      key = "order-confirmation"
  ))
  public void handle(OrderConfirmation confirmation) {
    System.out.println("[RECEIVED] - " + confirmation);
  }
}
