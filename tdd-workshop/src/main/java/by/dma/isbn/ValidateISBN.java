package by.dma.isbn;

import java.util.function.Predicate;

/**
 * The International Standard Book Number (ISBN) is a numeric commercial book identifier that is intended to be unique.
 * See <a href="https://en.wikipedia.org/wiki/ISBN">ISBN</a>.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
public class ValidateISBN implements Predicate<Integer> {

    @Override
    public boolean test(Integer value) {
        return true;
    }
}
