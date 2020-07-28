package by.dma;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
    Message message = jms.receive("time-queue");
    assertThat(message)
        .isInstanceOf(TextMessage.class);
    assertThat(((TextMessage) message).getText())
        .startsWith("Current Date & Time is: ");
  }
}

