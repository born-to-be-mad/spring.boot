package by.dma1979.recipes;

import by.dma1979.recipes.calculator.Calculator;
import by.dma1979.recipes.calculator.Operation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collection;

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

        var calculator = context.getBean(Calculator.class);
        calculator.calculate(137, 21, '+');
        calculator.calculate(137, 21, '*');
        calculator.calculate(137, 21, '-');
    }

    @Bean
    public Calculator calculator(Collection<Operation> operations) {
        return new Calculator(operations);
    }

}
