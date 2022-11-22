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
        assertThat(validator.test(140449116)).isTrue();
    }

}