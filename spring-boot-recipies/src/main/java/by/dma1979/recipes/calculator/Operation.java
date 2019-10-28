package by.dma1979.recipes.calculator;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:42
 * @since : 2019.10
 **/
public interface Operation {
    int apply(int lhs, int rhs);
    boolean handles(char op);
}
