package by.dma.intro.calculator;

public interface Operation {

    int apply(int lhs, int rhs);
    boolean handles(char op);

}
