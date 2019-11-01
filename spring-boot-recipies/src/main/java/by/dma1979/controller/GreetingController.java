package by.dma1979.controller;

import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:00
 * @since : 2019.11
 **/
@RestController
public class GreetingController {
    private static Faker faker = new Faker(new Locale("en"));

    @GetMapping( {"/", "/hello", "/hi", "/greetings"})
    public String hello() {
        return faker.chuckNorris().fact();
    }

    @GetMapping("login")
    public String viewLogin(@RequestParam String userName) {
        return String.format("Enter login details of %s", userName);
    }

    @PostMapping("login")
    public String doLogin(@RequestParam String userName) {
        return String.format("%s is logged-in", userName);
    }

    @GetMapping("/got")
    public String gameOfThrones() {
        return faker.gameOfThrones().quote();
    }

    @GetMapping("/animal")
    public String animal() {
        return String.format("Do you know %s?", faker.animal().name());
    }

}
