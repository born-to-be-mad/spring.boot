package by.dma1979.reactivedailyqa.websocket;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.stream.Stream;

/**
 * @author dzmitry.marudau
 * @since 2019.6
 */
@Component
public class PingProducer {
    public Flux<PingResponse> ping(PingRequest request) {
        return Flux
                .fromStream(Stream.generate(() ->
                        new PingResponse("Hello " + request.getName() + " @ " + LocalTime.now())))
                .delayElements(Duration.ofSeconds(1));

    }
}
