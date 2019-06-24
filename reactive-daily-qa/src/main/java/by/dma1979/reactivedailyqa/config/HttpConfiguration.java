package by.dma1979.reactivedailyqa.config;

import by.dma1979.reactivedailyqa.BranchRepository;
import by.dma1979.reactivedailyqa.model.Branch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author dzmitry.marudau
 * @since 2019.6
 */
@Configuration
public class HttpConfiguration {
    @Bean
    public RouterFunction<ServerResponse> routes(BranchRepository repository) {
        return route()
            //normal call via RestController
            .GET("/branches", serverRequest -> ok().body(repository.findAll(), Branch.class))
            .build();
    }

}

