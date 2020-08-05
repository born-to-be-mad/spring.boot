package by.dma;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import by.dma.entity.Order;
import by.dma.entity.OrderConfirmation;

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

  @Bean
  public Jackson2JsonMessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}

