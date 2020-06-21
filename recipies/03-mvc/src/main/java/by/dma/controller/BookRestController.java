package by.dma.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import by.dma.entity.Book;
import by.dma.service.BookService;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:30
 * @since : 2020.05
 **/
@RestController
@RequestMapping("/books")
public class BookRestController {

  private final BookService bookService;

  public BookRestController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public Iterable<Book> list() {
    return bookService.findAll();
  }

  @GetMapping("/{isbn}")
  public ResponseEntity<Book> get(@PathVariable("isbn") String isbn) {
    return bookService.find(isbn)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound()
                                            .build());
  }

  @PostMapping
  public Book create(@RequestBody Book book, UriComponentsBuilder uriBuilder) {
    Book created = bookService.create(book);
    URI newBookUri = uriBuilder.path("/books/{isbn}")
                               .build(created.getIsbn());
    return ResponseEntity.created(newBookUri)
                         .body(created)
                         .getBody();
  }

  @GetMapping("/throw500")
  public void error() {
    throw new NullPointerException("Dummy NullPointerException.");
  }

}
