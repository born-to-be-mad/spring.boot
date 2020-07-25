package by.dma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:37
 * @since : 2020.07
 **/
@Component
public class ScheduledPrinter {

  private static final Logger logger = LoggerFactory.getLogger(ScheduledPrinter.class);

  @Scheduled(fixedRate = 4000)
  public void printMessage() {
    logger.info("Scheduled print...");
  }
}
