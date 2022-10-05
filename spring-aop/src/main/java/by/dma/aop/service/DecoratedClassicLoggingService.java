package by.dma.aop.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import static by.dma.aop.service.ExecutionLogger.logExecution;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.10
 */
@Service
@Slf4j
public class DecoratedClassicLoggingService implements ExecutionService {

  @Override
  public void doBaseWork(String message) {
    logExecution("doBaseWork", message, () ->
      System.out.println("DecoratedClassicLoggingService#doBaseWork..." + message)
    );
  }

  @Override
  public void doAdvancedWork(String message, String requirements) {
    logExecution("doAdvancedWork", message, () -> {
      doBaseWork(message);
      System.out.println("DecoratedClassicLoggingService#doAdvancedWork... requires" + requirements);
    });
  }

}
