package by.dma.components.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculatorApplication {

  public static void main(String[] args) {
    var ctx = SpringApplication.run(CalculatorApplication.class, args);
    var calculator = ctx.getBean(Calculator.class);
    calculator.calculate(137, 21, '+');
    calculator.calculate(137, 21, '-');
    calculator.calculate(137, 21, '*');
  }

  //enabling overriding by setting spring.main.allow-bean-definition-overriding=true
/*    @Bean
    public Calculator calculator(Collection<Operation> operations) {
      return new Calculator(operations);
    }*/
}
