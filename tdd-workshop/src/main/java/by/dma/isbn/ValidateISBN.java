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
        if (isbn == null || isbn.length() != 10) {
            throw new NumberFormatException("Invalid ISBN length");
        }
        int total = 0;
        for (int i = 0; i < 10; i++) {
            var charAt = isbn.charAt(i);
            if (!Character.isDigit(charAt)) {
                if (i == 9 && charAt == 'X') {
                    total += 10;
                } else {
                    throw new NumberFormatException("ISBN contains non-numeric symbols");
                }
            } else {
                total += Character.getNumericValue(charAt) * (10 - i);
            }
        }

        return total % 11 == 0;
    }
}
