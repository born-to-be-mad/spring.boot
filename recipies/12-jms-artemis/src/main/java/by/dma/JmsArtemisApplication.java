package by.dma;

import java.util.Comparator;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:24
 * @since : 2020.05
 **/
@SpringBootApplication
public class JmsArtemisApplication {
  private static final String MSG = "\tName: %100s, Type: %s\n";

  public static void main(String[] args) {
    var ctx =
        SpringApplication.run(JmsArtemisApplication.class, args);

    System.out.println("# Beans: " + ctx.getBeanDefinitionCount());

    var names = ctx.getBeanDefinitionNames();
    Stream.of(names)
          .filter(name -> name.toLowerCase().contains("jms")
                          ||
                          ctx.getType(name).getName().contains("jms"))
          .sorted(Comparator.naturalOrder())
          .forEach(name -> {
            var bean = ctx.getBean(name);
            System.out.printf(MSG, name, bean.getClass().getSimpleName());
          });
  }
}
