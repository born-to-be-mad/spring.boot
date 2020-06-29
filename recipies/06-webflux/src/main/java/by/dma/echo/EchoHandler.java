package by.dma.echo;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Mono;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:32
 * @since : 2020.06
 **/
public class EchoHandler implements WebSocketHandler {
  @Override
  public Mono<Void> handle(WebSocketSession session) {
    return session.send(
            session.receive()
                    .map(msg -> "RECEIVED: " + msg.getPayloadAsText())
                    .map(session::textMessage)
    );
  }
}
