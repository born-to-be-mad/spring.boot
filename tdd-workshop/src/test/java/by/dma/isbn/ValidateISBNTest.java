package by.dma.isbn;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
class ValidateISBNTest {

    @Test
    void checkValidISBN() {
        var validator = new ValidateISBN();
        assertThat(validator.test("0140449116"))
                .as("The Odyssey - Homer")
                .isTrue();
        assertThat(validator.test("1617297577"))
                .as("Spring in Action, 6th Edition")
                .isTrue();
    }

    @Test
    void checkInvalidISBN() {
        var validator = new ValidateISBN();
        assertThat(validator.test("0140449117")).isFalse();
    }
}