package by.dma;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import by.dma.entity.Order;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:21
 * @since : 2020.07
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageSenderTest {

  @Autowired
  private JmsTemplate jms;

  @Test
  public void shouldSendMessage() throws JMSException {
/*  Message message = jms.receive("time-queue");
    assertThat(message).isInstanceOf(BytesMessage.class);
    BytesMessage msg = (BytesMessage) message;
    System.out.println("DMA:" + msg);*/

    String message = (String) jms.receiveAndConvert("time-queue");
    assertThat(message).startsWith("Current Date & Time is: ");

/*  assertThat(message).isInstanceOf(TextMessage.class);
    assertThat(((TextMessage) message).getText())
        .startsWith("Current Date & Time is: ");*/
  }

  @Test
  public void shouldReceiveOrderPlain() throws Exception {
    Message message = jms.receive("orders");
    assertThat(message).isInstanceOf(BytesMessage.class);
    BytesMessage msg = (BytesMessage) message;
    ObjectMapper mapper = new ObjectMapper();
    byte[] content = new byte[(int) msg.getBodyLength()];
    msg.readBytes(content);

    Order order = mapper.readValue(content, Order.class);
    assertThat(order).hasNoNullFieldsOrProperties();
  }

  @Test
  public void shouldReceiveOrderWithConversion() throws Exception {
    Order order = (Order) jms.receiveAndConvert("orders");
    System.out.println(order);
    assertThat(order).hasNoNullFieldsOrProperties();
  }
}

