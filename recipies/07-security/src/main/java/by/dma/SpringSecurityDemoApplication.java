package by.dma;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import by.dma.entity.Book;
import by.dma.service.BookService;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:24
 * @since : 2020.05
 **/
@SpringBootApplication
public class SpringSecurityDemoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context =
            SpringApplication.run(SpringSecurityDemoApplication.class, args);
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

}
