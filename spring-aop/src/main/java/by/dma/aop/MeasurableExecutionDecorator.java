package by.dma.aop;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * Decorates {@link Runnable} with measuring execution time.
 */
@Slf4j
@Builder
public class MeasurableExecutionDecorator implements Runnable {

  private final Runnable runnable;

  private final String executionDetails;

  private final Object message;

  private boolean measureMethodExecution;

  @Override
  public void run() {
    if (measureMethodExecution) {
      executeWithMeasure();
    } else {
      executeWithoutMeasure();
    }
  }

  private void executeWithoutMeasure() {
    log.debug("Start processing via [{}] of message: {}", executionDetails, message);
    runnable.run();
    log.debug("End processing via [{}]", executionDetails);
  }

  private void executeWithMeasure() {
    log.debug("Start processing via [{}] of message: {}", executionDetails, message);
    long time = System.nanoTime();
    try {
      runnable.run();
    } finally {
      time = System.nanoTime() - time;
    }
    if (log.isDebugEnabled()) {
      log.debug("'{}' operation has been processed in {} ms: message={}",
              executionDetails, (time / 1_000_000), message);
    } else {
      log.info("'{}' operation has been processed in {} ms",
              executionDetails, (time / 1_000_000));
    }
  }

}
