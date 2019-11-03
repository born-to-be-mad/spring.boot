package by.dma1979.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A book is defined by its ISBN number; it has a title and 1 or more authors.
 *
 * @author : Dzmitry Marudau
 * @created at : 00:32
 * @since : 2019.11
 **/
public class Book {
    private String isbn;
    private String title;
    private List<String> authors = new ArrayList<>();

    public Book() {
    }

    public Book(String isbn, String title, String... authors) {
        this.isbn = isbn;
        this.title = title;
        this.authors.addAll(Arrays.asList(authors));
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return String.format("Book [isbn=%s, title=%s, authors=%s]",
                this.isbn, this.title, this.authors);
    }
}