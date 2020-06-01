package by.dma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:24
 * @since : 2020.05
 **/
@SpringBootApplication
public class WebfluxDemoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context =
            SpringApplication.run(WebfluxDemoApplication.class, args);
  }

}
