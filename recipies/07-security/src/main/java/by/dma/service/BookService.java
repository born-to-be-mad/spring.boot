package by.dma.service;

import java.util.Optional;

import by.dma.entity.Book;

/**
 * Book Service.
 *
 * @author : Dzmitry Marudau
 * @created at : 00:35
 * @since : 2020.05
 **/
public interface BookService {
    Iterable<Book> findAll();

    Book create(Book book);

    Optional<Book> find(String isbn);

    Optional<Book> update(String isbn, Book book);

    Optional<Book> delete(String isbn);
}
