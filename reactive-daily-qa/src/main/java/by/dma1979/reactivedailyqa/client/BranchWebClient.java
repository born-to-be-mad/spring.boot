package by.dma1979.reactivedailyqa.client;

import by.dma1979.reactivedailyqa.model.Branch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
@Component
@RequiredArgsConstructor
public class BranchWebClient {

    private final WebClient webClient;

    public Flux<Branch> getAllBranches() {
        return webClient
            .get()
            .uri("http://localhost:8888/branches")
            .retrieve()
            .bodyToFlux(Branch.class);
    }
}
