package by.dma;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import by.dma.entity.Order;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
/**
 * @author : Dzmitry Marudau
 * @created at : 00:53
 * @since : 2020.08
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQSenderApplicationTest {

  @MockBean
  private RabbitTemplate rabbitTemplate;

  @Test
  public void shouldSendAtLeastASingleMessage() {
    verify(rabbitTemplate, atLeastOnce())
        .convertAndSend(
            eq("orders"),
            eq("new-order"),
            any(Order.class));
  }
}