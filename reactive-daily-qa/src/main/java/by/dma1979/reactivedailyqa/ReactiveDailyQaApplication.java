package by.dma1979.reactivedailyqa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveDailyQaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveDailyQaApplication.class, args);
        /*new SpringApplicationBuilder(ReactiveDailyQaApplication.class)
            .properties(Collections.singletonMap("server.port", "8081"))
            .run(args);*/
    }
}