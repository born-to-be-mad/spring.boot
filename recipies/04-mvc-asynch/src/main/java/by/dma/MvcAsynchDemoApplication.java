package by.dma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:24
 * @since : 2020.05
 **/
@SpringBootApplication
public class MvcAsynchDemoApplication implements WebMvcConfigurer {

  public static void main(String[] args) {
    ConfigurableApplicationContext context =
            SpringApplication.run(MvcAsynchDemoApplication.class, args);
  }

}
