package by.dma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Hello world!
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class BasicsDemoApplication {
  public static void main(String[] args) {
    ConfigurableApplicationContext context =
            SpringApplication.run(BasicsDemoApplication.class, args);
  }
}
