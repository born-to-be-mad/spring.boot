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
public class IntroDemoApplication {
  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(IntroDemoApplication.class, args);
    System.out.println("###############   BOOTING........");
    if (isDebugMode(args)) {
      System.out.println("### DEBUG MODE IS ACIVATED ....");
      printBeanDefinitions(context);
    }
  }

  private static void printBeanDefinitions(ConfigurableApplicationContext context) {
    System.out.printf("# There are %d bean definitions:%n", context.getBeanDefinitionCount());

    String[] beanNames = context.getBeanDefinitionNames();
    Arrays.sort(beanNames);

    Arrays.asList(beanNames)
            .forEach(System.out::println);
  }

  private static boolean isDebugMode(String[] args) {
    return args.length > 0 && args[0].equalsIgnoreCase("-debug");
  }
}
