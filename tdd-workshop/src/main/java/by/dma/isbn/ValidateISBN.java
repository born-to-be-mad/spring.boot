package by.dma.isbn;

import java.util.function.Predicate;

/**
 * The International Standard Book Number (ISBN) is a numeric commercial book identifier that is intended to be unique.
 * ISBN stands for International Standard Book Number and is a 13-digit number that identifies published books.
 * See <a href="https://en.wikipedia.org/wiki/ISBN">ISBN</a>.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
public class ValidateISBN implements Predicate<String> {

    @Override
    public boolean test(String isbn) {
        int total = 0;
        for (int i = 0; i < 10; i++) {
            total += isbn.charAt(i) * (10 - i);
        }
        return total % 11 == 0;
    }
}
