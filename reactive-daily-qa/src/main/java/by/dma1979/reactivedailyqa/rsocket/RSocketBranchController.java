package by.dma1979.reactivedailyqa.rsocket;

import by.dma1979.reactivedailyqa.websocket.PingProducer;
import by.dma1979.reactivedailyqa.websocket.PingRequest;
import by.dma1979.reactivedailyqa.websocket.PingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

/**
 * @author dzmitry.marudau
 * @since 2019.6
 */
@Controller
@RequiredArgsConstructor
public class RSocketBranchController {
    private final PingProducer producer;

    @MessageMapping("pinging")
    Flux<PingResponse> ping(PingRequest request) {
        return producer.ping(request);
    }
}
