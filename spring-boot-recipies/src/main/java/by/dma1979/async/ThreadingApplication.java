package by.dma1979.async;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:39
 * @since : 2019.12
 **/
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ThreadingApplication {
    public static void main(String[] args) throws IOException {
        new SpringApplicationBuilder(ThreadingApplication.class)
                .web(WebApplicationType.NONE)
                .build()
                .run(args);

        System.out.println("Press [ENTER] to quit:");
        System.in.read();
    }

    @Bean
    public ApplicationRunner startupRunner(HelloAsync hello) {
        return (args) -> {
            hello.printMessage();
        };
    }

}
