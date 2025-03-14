package by.dma.isbn;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void checkValidLongISBN() {
        var validator = new ValidateISBN();
        assertThat(validator.test("9781617298424"))
                .as("Spring Cloud Native")
                .isTrue();
        assertThat(validator.test("9781492046530"))
                .as("Kubernetes: Up and Running")
                .isTrue();
    }

    @Test
    void checkValidShortISBNEndingWIthX() {
        var validator = new ValidateISBN();
        assertThat(validator.test("080442957X"))
                .as("The Tales of Henry James ")
                .isTrue();
    }

    @Test
    void checkInvalidShortISBN() {
        var validator = new ValidateISBN();
        assertThat(validator.test("0140449117")).isFalse();
    }

    @Test
    void checkInvalidLongISBN() {
        var validator = new ValidateISBN();
        assertThat(validator.test("0123456789123")).isFalse();
    }

    @Test
    void checkBadLengthISBN() {
        var validator = new ValidateISBN();
        assertThatThrownBy(() -> validator.test("123456789"))
                .as("9 digit ISBN")
                .isInstanceOf(NumberFormatException.class);
    }

    @Test
    void checkNonNumericShortISBN() {
        var validator = new ValidateISBN();
        assertThatThrownBy(() -> validator.test("hello,java"))
                .as("Non numeric ISB of valid length")
                .isInstanceOf(NumberFormatException.class);
    }
}