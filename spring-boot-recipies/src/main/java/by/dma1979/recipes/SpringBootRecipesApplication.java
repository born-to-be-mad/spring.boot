package by.dma1979.recipes;

import by.dma1979.recipes.calculator.Calculator;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootRecipesApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootRecipesApplication.class, args);
        System.out.println("# Beans: " + context.getBeanDefinitionCount());
        String[] names = context.getBeanDefinitionNames();
        Arrays.sort(names);
        Arrays.asList(names).forEach(System.out::println);
        System.out.println("############################################");
        System.out.println("###############   STARTED    ###############");
        System.out.println("############################################");

    }

    @Bean
    public ApplicationRunner calculationRunner(Calculator calculator) {
        return args -> {
            calculator.calculate(137, 21, '+');
            calculator.calculate(137, 21, '*');
        };
    }

}
