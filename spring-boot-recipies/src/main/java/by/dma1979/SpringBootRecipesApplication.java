package by.dma1979;

import by.dma1979.calculator.Calculator;
import by.dma1979.entity.Book;
import by.dma1979.exception.CustomizedErrorAttributes;
import by.dma1979.service.BookService;
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
    public ApplicationRunner booksInitializer(BookService bookService) {
        System.out.println("### Initializing books ...");
        return args -> {
            bookService.create(
                    new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee"));
            bookService.create(
                    new Book("9780451524935", "1984", "George Orwell"));
            bookService.create(
                    new Book("9780618260300", "The Hobbit", "J.R.R. Tolkien"));
        };
    }

    @Bean
    public ApplicationRunner calculationRunner(Calculator calculator,
                                               @Value("${lhs}") int lhs,
                                               @Value("${rhs}") int rhs,
                                               @Value("${op:+}") char op) {
        System.out.println("### Calculator calculate ...");
        return args -> calculator.calculate(lhs, rhs, op);
    }

    @Bean
    public CustomizedErrorAttributes errorAttributes() {
        return new CustomizedErrorAttributes();
    }

}
