package by.dma1979.recipes;

import by.dma1979.recipes.calculator.Calculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootRecipesApplication {

    private static final Logger LOG = LogManager.getLogger(SpringBootRecipesApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootRecipesApplication.class, args);
        LOG.info("Hello from Log4j 2 - ConfigurableApplicationContext : {}", () -> context);

        System.out.println("###############   BOOTING........");
        System.out.printf("# There are %d bean definitions:%n", context.getBeanDefinitionCount());
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        Arrays.asList(beanNames).forEach(System.out::println);
        System.out.println("############################################");
        System.out.println("###############   THE END    ###############");
        System.out.println("############################################");

        LOG.info("###############   THE END    ###############");
    }

    @Bean
    public ApplicationRunner calculationRunner(Calculator calculator,
                                               @Value("${lhs}") int lhs,
                                               @Value("${rhs}") int rhs,
                                               @Value("${op:+}") char op) {
        return args -> calculator.calculate(lhs, rhs, op);
    }

}
