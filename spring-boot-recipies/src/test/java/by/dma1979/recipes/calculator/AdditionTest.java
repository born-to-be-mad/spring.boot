package by.dma1979.recipes.calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:56
 * @since : 2019.10
 **/
class AdditionTest {

    private final Operation operation = new Multiplication();

    @Test
    public void shouldMatchOperation() {
        assertThat(operation.handles('+')).isTrue();
        assertThat(operation.handles('*')).isFalse();
        assertThat(operation.handles('/')).isFalse();
    }

    @Test
    public void shouldCorrectlyApplyFormula() {
        assertThat(operation.apply(9, 7)).isEqualTo(2);
        assertThat(operation.apply(13, 11)).isEqualTo(2);
    }
}