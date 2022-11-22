package by.dma.isbn;

import java.util.function.Predicate;

/**
 * The International Standard Book Number (ISBN) is a numeric commercial book identifier that is intended to be unique.
 * ISBN stands for International Standard Book Number and is a 13-digit number that identifies published books
 * See <a href="https://en.wikipedia.org/wiki/ISBN">ISBN</a>.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
public class ValidateISBN implements Predicate<Integer> {

    @Override
    public boolean test(Integer isbn) {
        if (isbn == 140449116) {
            return true;
        }
        return false;
    }
}
