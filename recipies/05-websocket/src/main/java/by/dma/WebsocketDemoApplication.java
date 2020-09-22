package by.dma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import by.dma.echo.EchoHandler;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:24
 * @since : 2020.05
 **/
@SpringBootApplication
@EnableWebSocket
public class WebsocketDemoApplication implements WebSocketConfigurer {

  public static void main(String[] args) {
    ConfigurableApplicationContext context =
            SpringApplication.run(WebsocketDemoApplication.class, args);
  }

  @Bean
  public EchoHandler echoHandler() {
    return new EchoHandler();
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(echoHandler(), "/echo");
  }
}
