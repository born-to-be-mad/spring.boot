package by.dma1979.recipes.calculator;

import java.util.Collection;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:39
 * @since : 2019.10
 **/
public class Calculator {
    private final Collection<Operation> operations;

    public Calculator(Collection<Operation> operations) {
        this.operations = operations;
    }

    public void calculate(int lhs, int rhs, char op) {
        for (var operation : operations) {
            if (operation.handles(op)) {
                var result = operation.apply(lhs, rhs);
                System.out.printf("%d %s %d = %s%n", lhs, op, rhs, result);
                return;
            }
        }
        throw new IllegalArgumentException("Unknown operation " + op);
    }
}
