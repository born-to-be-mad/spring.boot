package by.dma.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import by.dma.entity.Book;
import by.dma.service.BookService;

/**
 * The service has been annotated with @Service so
 * that Spring Boot will detect it and create an instance of it.
 *
 * @author : Dzmitry Marudau
 * @created at : 00:36
 * @since : 2020.05
 **/
@Service
class InMemoryBookService implements BookService {
    private final Map<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return books.values();
    }

    @Override
    public Book create(Book book) {
        books.put(book.getIsbn(), book);
        return book;
    }

    @Override
    public Optional<Book> find(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    @Override
    public Optional<Book> update(String isbn, Book book) {
        return Optional.ofNullable(books.computeIfPresent(isbn, (k, v) -> book));
    }

    @Override
    public Optional<Book> delete(String isbn) {
        return Optional.ofNullable(books.remove(isbn));
    }
}
