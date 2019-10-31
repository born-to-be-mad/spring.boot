package by.dma1979.recipes.service;

import by.dma1979.recipes.entity.Book;

import java.util.Optional;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:35
 * @since : 2019.11
 **/
public interface BookService {
    Iterable<Book> findAll();

    Book create(Book book);

    Optional<Book> find(String isbn);
}
