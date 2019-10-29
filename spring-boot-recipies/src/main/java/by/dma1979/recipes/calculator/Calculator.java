package by.dma1979.recipes.calculator;

import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:39
 * @since : 2019.10
 **/
@Component
public class Calculator {
    private final Collection<Operation> operations;

    public Calculator(Collection<Operation> operations) {
        this.operations = operations;
    }

    public void calculate(int lhs, int rhs, char mathOperation) {
        for (var operation : operations) {
            if (operation.handles(mathOperation)) {
                var result = operation.apply(lhs, rhs);
                System.out.printf("%d %s %d = %s%n", lhs, mathOperation, rhs, result);
                return;
            }
        }
        throw new IllegalArgumentException("Unknown operation " + mathOperation);
    }
}
