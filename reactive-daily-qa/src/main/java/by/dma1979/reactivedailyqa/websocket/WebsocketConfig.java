package by.dma1979.reactivedailyqa.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.Map;

/**
 * @author dzmitry.marudau
 * @since 2019.6
 */
@Configuration
public class WebsocketConfig {
    @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler handler) {
        return new SimpleUrlHandlerMapping() {
            {
                setUrlMap(Map.of("/ws/ping", handler));
                setOrder(10);
            }

        };
    }

    @Bean
    WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    @Bean
    WebSocketHandler webSocketHandler(PingProducer producer) {
        return session -> {
            var response = session.receive()
                    .map(WebSocketMessage::getPayloadAsText)
                    .map(PingRequest::new)
                    .flatMap(producer::ping)
                    .map(PingResponse::getMessage)
                    .map(session::textMessage);
            return session.send(response);
        };
    }

}
