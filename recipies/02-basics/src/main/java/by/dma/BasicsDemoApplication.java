package by.dma;

import by.dma.components.calculator.Calculator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@SpringBootApplication
public class BasicsDemoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context =
            SpringApplication.run(BasicsDemoApplication.class, args);
  }

  /*public static void main(String[] args) {
    var ctx = SpringApplication.run(CalculatorApplication.class, args);
    var calculator = ctx.getBean(Calculator.class);
    calculator.calculate(137, 21, '+');
    calculator.calculate(137, 21, '-');
    calculator.calculate(137, 21, '*');
  }*/

  @Bean
  public ApplicationRunner calculationRunner(
          Calculator calculator,
          @Value("${left}") int lhs,
          @Value("${right}") int rhs,
          @Value("${operation}") char op) {
    return args -> {
      System.out.println("### Calculate using application-properties:");
      calculator.calculate(lhs, rhs, op);

      System.out.println("### All operations in action:");
      calculator.calculate(138, 21, '+');
      calculator.calculate(137, 21, '-');
      calculator.calculate(137, 21, '*');
    };
  }


/*
   // enabling overriding by setting
   // spring.main.allow-bean-definition-overriding=true
    @Bean
    public Calculator calculator(Collection<Operation> operations) {
      return new Calculator(operations);
    }
*/
}
