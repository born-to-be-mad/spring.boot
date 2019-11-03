package by.dma1979.controller;

import by.dma1979.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : Dzmitry Marudau
 * @created at : 23:44
 * @since : 2019.11
 **/
@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books.html")
    public String all(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list.html";
    }

    @GetMapping(value = "/books.html", params = "isbn")
    public String get(@RequestParam("isbn") String isbn, Model model) {
        bookService.find(isbn)
                .ifPresent(book -> model.addAttribute("book", book));
        return "books/details";
    }
}
