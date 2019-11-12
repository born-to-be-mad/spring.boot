package by.dma1979.calculator;

import org.springframework.stereotype.Component;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:43
 * @since : 2019.10
 **/
@Component
class Addition implements Operation {
    @Override
    public int apply(int lhs, int rhs) {
        return lhs + rhs;
    }

    @Override
    public boolean handles(char op) {
        return '+' == op;
    }
}

