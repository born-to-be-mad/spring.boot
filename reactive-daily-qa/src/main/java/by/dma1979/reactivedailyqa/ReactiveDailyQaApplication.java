package by.dma1979.reactivedailyqa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class ReactiveDailyQaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveDailyQaApplication.class, args);
    }

}

@Configuration
class WebsocketConfig {
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

@Data
@AllArgsConstructor
@NoArgsConstructor
class PingRequest {
    String name;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class PingResponse {
    String message;
}

@Component
class PingProducer {
    Flux<PingResponse> ping(PingRequest request) {
        return Flux
                .fromStream(Stream.generate(() ->
                        new PingResponse("Hello " + request.getName() + " @ " + LocalTime.now())))
                .delayElements(Duration.ofSeconds(1));

    }
}


@Configuration
class HttpConfiguration {
    @Bean
    RouterFunction<ServerResponse> routes(BranchRepository repository) {
        return route()
                .GET("/branches", serverRequest -> ok().body(repository.findAll(), Branch.class))
                .build();
    }
}

@Component
@Log4j2
@RequiredArgsConstructor
class DataInitializer {
    private final BranchRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void go() {

        var branchFlux = Flux
                .just("2019.1", "2019.2", "2019.3", "2019.4") // Flux<String>
                .map(branch -> new Branch(null, branch)) // Flux<Branch>
                .flatMap(repository::save); //Flux<Branch>

        this.repository
                .deleteAll()
                .thenMany(branchFlux)
                .thenMany(repository.findAll())
                .subscribe(log::info);
    }

}


interface BranchRepository extends ReactiveCrudRepository<Branch, String> {
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
class Branch {
    @Id
    private String id;

    private String name;

}