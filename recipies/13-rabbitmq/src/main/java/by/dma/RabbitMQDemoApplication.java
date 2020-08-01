package by.dma;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:55
 * @since : 2020.08
 **/
@SpringBootApplication
@EnableScheduling
public class RabbitMQDemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(RabbitMQDemoApplication.class, args);
  }
}

@Component
class HelloWorldSender {
  private final RabbitTemplate rabbit;

  HelloWorldSender(RabbitTemplate rabbit) {
    this.rabbit = rabbit;
  }

  @Scheduled(fixedRate = 500)
  public void sendTime() {

    rabbit.convertAndSend("hello", "Hello World, from Spring Boot 2, over RabbitMQ!");
  }

}
