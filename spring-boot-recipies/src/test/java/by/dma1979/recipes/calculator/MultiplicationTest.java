package by.dma1979.recipes.calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:54
 * @since : 2019.10
 **/
public class MultiplicationTest {
    private final Operation operation = new Multiplication();

    @Test
    public void shouldMatchOperation() {
        assertThat(operation.handles('*')).isTrue();
        assertThat(operation.handles('/')).isFalse();
    }

    @Test
    public void shouldCorrectlyApplyFormula() {
        assertThat(operation.apply(9, 7)).isEqualTo(63);
        assertThat(operation.apply(13, 11)).isEqualTo(143);
    }
}