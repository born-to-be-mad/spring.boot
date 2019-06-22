package by.dma1979.reactivedailyqa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveDailyQaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveDailyQaApplication.class, args);
    }

}

@RestController
@RequiredArgsConstructor
class BranchController {
    private final BranchRepository repository;

    @RequestMapping("/branches")
    Flux<Branch> listFlux() {
        return repository.findAll();
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