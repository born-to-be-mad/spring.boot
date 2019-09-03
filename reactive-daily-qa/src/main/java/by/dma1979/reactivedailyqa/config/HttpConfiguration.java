package by.dma1979.reactivedailyqa.config;

import by.dma1979.reactivedailyqa.BranchRepository;
import by.dma1979.reactivedailyqa.client.BranchWebClient;
import by.dma1979.reactivedailyqa.model.Branch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author dzmitry.marudau
 * @since 2019.6
 */
@Configuration
public class HttpConfiguration {
    @Bean
    WebClient client(WebClient.Builder builder) {
        return builder.build();
    }

    @Bean
    public RouterFunction<ServerResponse> routes(BranchWebClient branchWebClient, BranchRepository repository) {
        return route()
            //normal call via RestController
            .GET("/branches", serverRequest -> ok().body(repository.findAll(), Branch.class))
            // call via WebClient to get branch names
            .GET("/branch/names", serverRequest -> {
                Flux<String> branchNamesFlux = branchWebClient
                    .getAllBranches()
                    .map(Branch::getName)
                    .onErrorResume(throwable -> Flux.just("Problem while receiving data!"))
                    .retryBackoff(10, Duration.ofSeconds(1));
                return ServerResponse.ok().body(branchNamesFlux, String.class);
            })
            .build();
    }

}

