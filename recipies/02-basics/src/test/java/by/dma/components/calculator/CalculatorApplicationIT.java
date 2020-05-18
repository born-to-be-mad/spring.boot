package by.dma.components.calculator;
import by.dma.BasicsDemoApplication;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BasicsDemoApplication.class)
public class CalculatorApplicationIT {

  @Rule
  public OutputCaptureRule output = new OutputCaptureRule();

  @Autowired
  private Calculator calculator;

  @Test(expected = IllegalArgumentException.class)
  public void doingDivisionShouldFail() {
    calculator.calculate(12,13, '/');
  }

  @Test
  public void doingMultiplicationShouldSucceed() {
    calculator.calculate(12,13, '*');
    output.expect(Matchers.containsString("12 * 13 = 156"));
  }

}
