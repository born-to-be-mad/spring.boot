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

    private static final int LONG_ISBN_LENGTH = 13;

    private static final int SHORT_ISBN_LENGTH = 10;

    private static final int LONG_ISBN_MULTIPLIER = 10;

    private static final int SHORT_ISBN_MULTIPLIER = 11;

    @Override
    public boolean test(String isbn) {
        if (isbn != null && isbn.length() == LONG_ISBN_LENGTH) {
            return testLongIsbn(isbn);
        } else if (isbn != null && isbn.length() == SHORT_ISBN_LENGTH) {
            return testShortIsbn(isbn);
        }
        throw new NumberFormatException("Invalid ISBN length(10 or 13 characters)");
    }

    private static boolean testShortIsbn(String isbn) {
        int total = 0;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            var charAt = isbn.charAt(i);
            if (!Character.isDigit(charAt)) {
                if (i == 9 && charAt == 'X') {
                    total += 10;
                } else {
                    throw new NumberFormatException("ISBN contains non-numeric symbols");
                }
            } else {
                total += Character.getNumericValue(charAt) * (SHORT_ISBN_LENGTH - i);
            }
        }

        return total % SHORT_ISBN_MULTIPLIER == 0;
    }

    private static boolean testLongIsbn(String isbn) {
        int total = 0;
        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            var charAt = isbn.charAt(i);
            if (i % 2 == 0) {
                total += Character.getNumericValue(charAt);
            } else {
                total += Character.getNumericValue(charAt) * 3;
            }
        }
        return total % LONG_ISBN_MULTIPLIER == 0;
    }
}
