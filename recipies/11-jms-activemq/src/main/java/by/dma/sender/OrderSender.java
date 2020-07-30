package by.dma.sender;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import by.dma.entity.Order;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:27
 * @since : 2020.07
 **/
@Component
public class OrderSender {
  private final JmsTemplate jms;

  OrderSender(JmsTemplate jms) {
    this.jms = jms;
  }

  @Scheduled(fixedRate = 1000)
  public void sendTime() {
    var id = UUID.randomUUID().toString();
    var amount = ThreadLocalRandom.current().nextDouble(1000.00d);
    var order = new Order(id, BigDecimal.valueOf(amount));
    jms.convertAndSend("orders", order);
  }
}
