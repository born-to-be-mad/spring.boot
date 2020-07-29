package by.dma.sender;

import java.time.LocalDateTime;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:07
 * @since : 2020.07
 **/
@Component
public class MessageSender {

  private final JmsTemplate jms;

  MessageSender(JmsTemplate jms) {
    this.jms = jms;
  }

  @Scheduled(fixedRate = 1000)
  public void sendTime() {
    jms.convertAndSend("time-queue",
                       "Current Date & Time is: " + LocalDateTime.now());
  }
}
