package by.dma.components.calculator.operation;

import org.springframework.stereotype.Component;

@Component
class Substruction implements Operation {

  @Override
  public int apply(int lhs, int rhs) {
    return lhs - rhs;
  }

  @Override
  public boolean handles(char op) {
    return '-' == op;
  }
}
