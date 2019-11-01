package by.dma1979.calculator;

import by.dma1979.SpringBootRecipesApplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


/**
 * @author : Dzmitry Marudau
 * @created at : 00:10
 * @since : 2019.10
 **/
@ExtendWith({OutputCaptureExtension.class})
@SpringBootTest(classes = SpringBootRecipesApplication.class)
public class CalculatorWithMockIT {

    @MockBean(name = "division")
    private Operation mockOperation;

    @Autowired
    private Calculator calculator;

    @Test
    public void calculatorShouldHave3Operations() {
        /*When a bean with that name cannot be found,
                the mocked bean will be registered as a new instance of that bean.*/
        Object operations =
                ReflectionTestUtils.getField(calculator, "operations");
        Assertions.assertThat((Collection) operations).hasSize(3);
    }

    @Test
    public void mockDivision(CapturedOutput output) {
        when(mockOperation.handles('/')).thenReturn(true);
        when(mockOperation.apply(14, 7)).thenReturn(2);
        calculator.calculate(14,7, '/');
        assertThat(output.getOut()).contains("14 / 7 = 2");
    }
}
