package spring.boot;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.boot.model.Card;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
@Slf4j
@Component
public class CardInitializer implements CommandLineRunner {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Start initialization... ");
        Faker fakeGenerator = new Faker();
        for (int i = 0; i < 10; i++) {
            Card card = new Card();
            card.setAuthor(fakeGenerator.book().author());
            card.setName(fakeGenerator.pokemon().name());
            card.setTranslation(fakeGenerator.pokemon().location());
            cardRepository.save(card);
        }
        log.info("Initialization finished");
    }
}
