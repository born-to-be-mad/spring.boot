package by.dma1979.service;

import by.dma1979.entity.Book;

import java.util.Optional;

/**
 * Book Service.
 *
 * @author : Dzmitry Marudau
 * @created at : 00:35
 * @since : 2019.11
 **/
public interface BookService {
    Iterable<Book> findAll();

    Book create(Book book);

    Optional<Book> find(String isbn);

    Optional<Book> update(String isbn, Book book);

    Optional<Book> delete(String isbn);
}
