import by.dma.BasicsDemoApplication;
import by.dma.components.calculator.Calculator;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BasicsDemoApplication.class)
public class CalculatorApplicationIT {

  @Rule
  public OutputCapture capture = new OutputCapture();

  @Autowired
  private Calculator calculator;

  @Test(expected = IllegalArgumentException.class)
  public void doingDivisionShouldFail() {
    calculator.calculate(12,13, '/');
  }

  @Test
  public void doingMultiplicationShouldSucceed() {
    calculator.calculate(12,13, '*');
    capture.expect(Matchers.containsString("12 * 13 = 156"));
  }

}
