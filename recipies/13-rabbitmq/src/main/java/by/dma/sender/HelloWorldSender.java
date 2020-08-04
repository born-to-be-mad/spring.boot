package by.dma.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldSender {
  private final RabbitTemplate rabbit;

  HelloWorldSender(RabbitTemplate rabbit) {
    this.rabbit = rabbit;
  }

  @Scheduled(fixedRate = 500)
  public void sendTime() {

    rabbit.convertAndSend("hello", "Hello World, from Spring Boot 2, over RabbitMQ!");
  }

}
