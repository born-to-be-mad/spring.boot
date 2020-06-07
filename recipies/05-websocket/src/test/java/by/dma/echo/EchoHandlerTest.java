package by.dma.echo;

import org.junit.Test;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.*;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:08
 * @since : 2020.06
 **/
public class EchoHandlerTest {
  private final EchoHandler handler = new EchoHandler();

  @Test
  public void shouldEchoMessage() throws Exception {
    var mockSession = mock(WebSocketSession.class);
    var msg = new TextMessage("Test");
    handler.handleTextMessage(mockSession, msg);
    verify(mockSession, times(1))
            .sendMessage(eq(new TextMessage("RECEIVED: Test")));
  }
}
