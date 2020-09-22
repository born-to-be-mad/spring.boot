package by.dma.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:49
 * @since : 2020.07
 **/
@Component
public class MessageListener {

/*  @JmsListener(destination = "time-queue")
  public void handle(Message msg) throws JMSException {
    Assert.state(msg instanceof TextMessage, "Can only handle TextMessage.");
    System.out.println("[RECEIVED] - " + ((TextMessage) msg).getText());
  }*/

  @JmsListener(destination = "time-queue")
  public void handle(String msg) {
    System.out.println("[RECEIVED] from 'time-queue': " + msg);
  }
}
