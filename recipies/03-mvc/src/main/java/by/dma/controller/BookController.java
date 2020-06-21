package by.dma.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.dma.entity.Book;
import by.dma.service.BookService;

/**
 * Book Controller to manage books.
 *
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
        return "books/list";
    }

    @GetMapping(value = "/books.html", params = "isbn")
    public String get(@RequestParam("isbn") String isbn, Model model) {
        bookService.find(isbn)
            .ifPresent(book -> model.addAttribute("book", book));
        return "books/details";
    }

    @GetMapping(value = "/books.show.add.html")
    public String showAddForm(Book book) {
        return "books/add";
    }

    @PostMapping(value = "/books.add.html")
    public String add(@Valid Book book, Model model) {
        bookService.create(book);
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @GetMapping(value = "/books.show.edit.html/{isbn}")
    public String showEditForm(@PathVariable("isbn") String isbn, Model model) {
        Book book = bookService.find(isbn)
            .orElseThrow(() -> new IllegalArgumentException("Invalid books ISBN:" + isbn));
        model.addAttribute("book", book);
        return "books/update";
    }

    @PostMapping(value = "/books.edit.html/{isbn}")
    public String edit(@PathVariable("isbn") String isbn, @Valid Book book, Model model) {
        bookService.update(isbn, book);
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @GetMapping(value = "/books.delete.html/{isbn}")
    public String delete(@PathVariable("isbn") String isbn, Model model) {
        bookService.delete(isbn);
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }
}
