package by.dma.crudwebapp.controller;

import by.dma.crudwebapp.controller.model.Card;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * This initializer gets executed once the application started.
 * @author dzmitry.marudau
 * @since 2019.7
 */
@Slf4j
@Component
public class CardInitializer implements CommandLineRunner {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting to initialize data ...");
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Card card = createFakeCard(faker);
            cardRepository.save(card);
        }
        log.info("Data initialization finished");
    }

    private Card createFakeCard(Faker faker) {
        Card card = new Card();
        card.setAuthor(faker.book().author());
        card.setDefinition(faker.gameOfThrones().character());
        card.setContent(faker.gameOfThrones().quote());
        card.setHashTag("#" + UUID.randomUUID().toString());
        card.setCreationDate(faker.date().past(1000, 10, TimeUnit.DAYS));
        return card;
    }
}
