package by.dma.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculatorApplication {

  public static void main(String[] args) {
    var ctx = SpringApplication.run(CalculatorApplication.class, args);
    var calculator = ctx.getBean(Calculator.class);
    calculator.calculate(137, 21, '+');
    calculator.calculate(137, 21, '*');
    //calculator.calculate(137, 2`1, '-');
  }

/*  @Bean
  public Calculator1 calculator1(Collection<Operation> operations) {
    return new Calculator1(operations);
  }*/
}
