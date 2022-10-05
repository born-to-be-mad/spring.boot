package by.dma.aop.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.10
 */
@Service
@Slf4j
public class ClassicLoggingExecutionService implements ExecutionService {

  @Override
  public void doBaseWork(String message) {
    log.info("Start execution of doBaseWork method with message: {}", message);
    System.out.println("ClassicLoggingService#doBaseWork..." + message);
    log.info("Finish execution of doBaseWork method with message: {}", message);
  }

  @Override
  public void doAdvancedWork(String message, String requirements) {
    log.info("Start execution of doAdvancedWork method with message: {}", message);
    doBaseWork(message);
    System.out.println("ClassicLoggingService#doAdvancedWork... requires " + requirements);
    log.info("Finish execution of doAdvancedWork method with message: {}", message);
  }

}
