package by.dma.aop.service;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.10
 */
public class ExecutionServiceImpl implements ExecutionService {

  @Override public void doBaseWork(String message) {
    System.out.println("ExecutionServiceImpl#doBaseWork..." + message);
  }

  @Override public void doAdvancedWork(String message, String requirements) {
    doBaseWork(message);
    System.out.println("ExecutionServiceImpl#doAdvancedWork... requires " + requirements);
  }
}
