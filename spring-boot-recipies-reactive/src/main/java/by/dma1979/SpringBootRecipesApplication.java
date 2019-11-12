package by.dma1979;

import by.dma1979.calculator.Calculator;
import by.dma1979.entity.Book;
import by.dma1979.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRecipesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRecipesApplication.class, args);
    }
    private static final Logger LOG = LogManager.getLogger(SpringBootRecipesApplication.class);

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

}
