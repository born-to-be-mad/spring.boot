package by.dma.aop.service;

import org.springframework.stereotype.Service;

import by.dma.aop.aspect.ExclusiveLock;
import by.dma.aop.aspect.LogMethod;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.10
 */
@Service
public class ExecutionServiceImpl implements ExecutionService {

  @Override
  @LogMethod
  public void doBaseWork(String message) {
    System.out.println("ExecutionServiceImpl#doBaseWork..." + message);
  }

  @Override
  @LogMethod
  public void doAdvancedWork(String message, String requirements) {
    doBaseWork(message);
    System.out.println("ExecutionServiceImpl#doAdvancedWork... requires " + requirements);
  }

  @LogMethod
  @ExclusiveLock
  public void doExclusiveWork(String message) {
    System.out.println("ExecutionServiceImpl#doExclusiveWork..." + message);
  }
}
