package by.dma.clr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

@SpringBootApplication
@Profile("fizzbuzz")
public class FizzBuzzApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FizzBuzzApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i < 101; i++) {
            String result = "";
            result += (i % 3) == 0 ? "Fizz" : "";
            result += (i % 5) == 0 ? "Buzz" : "";
            System.out.println(!result.isEmpty() ? result : i);
        }

    }
}
