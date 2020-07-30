package by.dma;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.scheduling.annotation.EnableScheduling;

import by.dma.entity.Order;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:24
 * @since : 2020.05
 **/
@SpringBootApplication
@EnableScheduling
public class JmsActiveMQApplication {

  private static final String MSG = "\tName: %100s, Type: %s\n";

  public static void main(String[] args) {
    var ctx = SpringApplication.run(JmsActiveMQApplication.class, args);

    System.out.println("# Beans: " + ctx.getBeanDefinitionCount());
    var names = ctx.getBeanDefinitionNames();
    Stream.of(names)
          .filter(name -> name.toLowerCase().contains("jms"))
          .sorted(Comparator.naturalOrder())
          .forEach(name -> {
            Object bean = ctx.getBean(name);
            System.out.printf(MSG, name, bean.getClass().getSimpleName());
          });
  }

  @Bean
  public MappingJackson2MessageConverter messageConverter() {
    var messageConverter = new MappingJackson2MessageConverter();
    messageConverter.setTypeIdPropertyName("content-type");
    messageConverter.setTypeIdMappings(
        Collections.singletonMap("order", Order.class)
    );
    //messageConverter.setTargetType(MessageType.TEXT);
    return messageConverter;
  }
}
