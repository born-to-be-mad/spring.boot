package by.dma1979.recipes.calculator;

import by.dma1979.recipes.SpringBootRecipesApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
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

}
