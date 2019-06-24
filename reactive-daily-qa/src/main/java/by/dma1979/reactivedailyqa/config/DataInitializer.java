package by.dma1979.reactivedailyqa.config;

import by.dma1979.reactivedailyqa.BranchRepository;
import by.dma1979.reactivedailyqa.model.Branch;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * @author dzmitry.marudau
 * @since 2019.6
 */
@Component
@Log4j2
@RequiredArgsConstructor
public class DataInitializer {
    private final BranchRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void go() {

        var branchFlux = Flux
                .just("2019.1", "2019.2", "2019.3", "2019.6") // Flux<String>
                .map(branch -> new Branch(null, branch)) // Flux<Branch>
                .flatMap(repository::save); //Flux<Branch>

        this.repository
                .deleteAll()
                .thenMany(branchFlux)
                .thenMany(repository.findAll())
                .subscribe(log::info);
    }

}
