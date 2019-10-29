package by.dma1979.recipes.calculator;

import by.dma1979.recipes.SpringBootRecipesApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
@SpringBootTest(classes = SpringBootRecipesApplication.class)
public class CalculatorIT {

    @Autowired
    private Calculator calculator;

    @Test
    public void doingDivisionShouldFail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculate(12, 13, '/');
        });
    }

    @Test
    public void doingMultiplicationShouldSucceed(CapturedOutput output) {
        calculator.calculate(12,13, '*');
        assertThat(output.getOut()).contains("12 * 13 = 156");
    }

}
