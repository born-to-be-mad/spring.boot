package by.dma.isbn;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        assertThat(validator.test(140449116))
                .as("The Odyssey - Homer")
                .isTrue();
        assertThat(validator.test(1617297571))
                .as("Spring in Action, 6th Edition")
                .isTrue();
    }

    @Test
    void checkInvalidISBN() {
        var validator = new ValidateISBN();
        assertThat(validator.test(140449117)).isFalse();
    }
}