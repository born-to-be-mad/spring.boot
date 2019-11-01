package by.dma.crudwebapp;

import by.dma.crudwebapp.model.User;
import by.dma.crudwebapp.repository.UserRepository;
import com.github.javafaker.Faker;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * @author dzmitry.marudau
 * @since 2019.08
 */
@Component
public class CrudWebAppDataLoader implements CommandLineRunner {

    private static final Logger LOG = getLogger(CrudWebAppDataLoader.class);

    private static final int INITIAL_AMOUNT_OF_USERS = 10;

    private Faker faker = new Faker();

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... strings) throws Exception {
/*        LOG.info("Loading data...");
        for (int i = 0; i < INITIAL_AMOUNT_OF_USERS; i++) {
            userRepository.save(generateUser());
        }
        userRepository.findAll().forEach(System.out::println);*/

    }

    private User generateUser() {
        User user = new User();
        user.setLogin(faker.artist().name());
        user.setName(faker.funnyName().name());
        user.setEmail(faker.internet().emailAddress());
        return user;
    }
}
