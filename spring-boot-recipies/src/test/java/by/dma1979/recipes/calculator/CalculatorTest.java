package by.dma1979.recipes.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.*;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:59
 * @since : 2019.10
 **/
class CalculatorTest {

    private Calculator calculator;
    private Operation mockOperation;

    @BeforeEach
    public void setup() {
        mockOperation = Mockito.mock(Operation.class);
        calculator = new Calculator(Collections.singletonList(mockOperation));
    }

    @Test
    public void throwExceptionWhenNoSuitableOperationFound() {
        when(mockOperation.handles(anyChar())).thenReturn(false);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(2, 2, '*');
        });
    }

    @Test
    public void shouldCallApplyMethodWhenSuitableOperationFound() {
        when(mockOperation.handles(anyChar()))
                .thenReturn(true);
        when(mockOperation.apply(9, 7))
                .thenReturn(63);

        calculator.calculate(9, 7, '*');
        verify(mockOperation, times(1))
                .apply(9, 7);

    }
}