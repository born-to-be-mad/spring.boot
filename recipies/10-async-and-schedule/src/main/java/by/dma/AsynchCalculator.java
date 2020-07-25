package by.dma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:29
 * @since : 2020.07
 **/
@Component
public class AsynchCalculator {
  private static final Logger logger = LoggerFactory.getLogger(AsynchCalculator.class);

  @Async
  public void calculate() throws InterruptedException {
    logger.info("Calculating...");
    Thread.sleep(1500);
    logger.info("Calculation done!");
  }
}
