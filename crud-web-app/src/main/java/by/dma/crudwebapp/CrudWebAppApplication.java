package by.dma.crudwebapp;

import by.dma.crudwebapp.model.User;
import by.dma.crudwebapp.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudWebAppApplication {

    private static final int INITIAL_AMOUNT_OF_USERS = 10;

    private Faker faker = new Faker();

    public static void main(String[] args) {
        SpringApplication.run(CrudWebAppApplication.class, args);
    }

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            for (int i = 0; i < INITIAL_AMOUNT_OF_USERS; i++) {
                userRepository.save(generateUser());
            }
            userRepository.findAll().forEach(System.out::println);
        };
    }

    private User generateUser() {
        User user = new User();
        user.setLogin(faker.artist().name());
        user.setName(faker.funnyName().name());
        user.setEmail(faker.internet().emailAddress());
        return user;
    }

}
