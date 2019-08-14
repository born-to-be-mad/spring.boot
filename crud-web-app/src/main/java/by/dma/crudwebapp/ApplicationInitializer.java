package by.dma.crudwebapp;

import by.dma.crudwebapp.model.Card;
import by.dma.crudwebapp.repository.CardRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
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
public class ApplicationInitializer implements CommandLineRunner {
    private static final int INITIAL_AMOUNT_OF_CARDS = 10;

    private final CardRepository cardRepository;

    private final Faker faker;

    public ApplicationInitializer(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
        faker = new Faker();
    }

    @Override
    public void run(String... args) {
        log.info("Starting to initialize data ...");
        for (int i = 0; i < INITIAL_AMOUNT_OF_CARDS; i++) {
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
