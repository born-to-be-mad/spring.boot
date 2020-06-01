package by.dma.echo;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:35
 * @since : 2020.06
 **/
public class EchoHandler extends TextWebSocketHandler {
  @Override
  public void afterConnectionEstablished(WebSocketSession session)
          throws Exception {
    session.sendMessage(new TextMessage("CONNECTION ESTABLISHED"));
  }

  @Override
  protected void handleTextMessage(
          WebSocketSession session,
          TextMessage message) throws Exception {
    var msg = message.getPayload();
    session.sendMessage(new TextMessage("RECEIVED: " + msg));
  }
}
