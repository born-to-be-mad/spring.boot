package by.dma1979.calculator;

import org.springframework.stereotype.Component;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:44
 * @since : 2019.10
 **/
@Component
class Multiplication implements Operation {
    @Override
    public int apply(int lhs, int rhs) {
        return lhs * rhs;
    }

    @Override
    public boolean handles(char op) {
        return '*' == op;
    }
}
