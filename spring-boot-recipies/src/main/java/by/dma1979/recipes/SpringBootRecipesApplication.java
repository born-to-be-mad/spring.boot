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
        System.out.println("###############   BOOTING........");
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootRecipesApplication.class, args);
        System.out.printf("# There are %d bean definitions:%n", context.getBeanDefinitionCount());
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        Arrays.asList(beanNames).forEach(System.out::println);
        System.out.println("############################################");
        System.out.println("###############   THE END    ###############");
        System.out.println("############################################");
    }

    @Bean
    public ApplicationRunner calculationRunner(Calculator calculator) {
        return args -> {
            calculator.calculate(9, 7, '+');
            calculator.calculate(9, 7, '*');
        };
    }

}
