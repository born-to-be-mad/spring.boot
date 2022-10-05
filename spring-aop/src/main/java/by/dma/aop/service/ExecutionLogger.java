package by.dma.aop.service;

import org.springframework.util.StopWatch;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Wrap logging in utility methods and execute it.
 *
 * @author dzmitry.marudau
 * @since 2022.10
 */
@UtilityClass
@Slf4j
public class ExecutionLogger {

  public static void logExecution(String method, String arguments, Runnable code) {
    final StopWatch stopWatch = new StopWatch();
    try {
      stopWatch.start();
      code.run();
    } catch (Exception e) {
      log.error("Method {} with arguments {} execution fail(running {} ns)",
              method, arguments, stopWatch.getTotalTimeNanos());
      throw e;
    } finally {
      stopWatch.stop();
      log.info("Method {} with arguments {} executed (running {} ns)",
              method, arguments, stopWatch.getTotalTimeNanos());
    }
  }

}
