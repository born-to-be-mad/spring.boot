package by.dma.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A book is defined by its ISBN number; it has a title and 1 or more authors.
 *
 * @author : Dzmitry Marudau
 * @created at : 00:32
 * @since : 2020.05
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Book {
    private String isbn;
    private String title;
    private List<String> authors = new ArrayList<>();

    public Book(String isbn, String title, String... authors) {
        this.isbn = isbn;
        this.title = title;
        this.authors.addAll(Arrays.asList(authors));
    }

}
