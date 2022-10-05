package by.dma.aop.service;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.10
 */
public interface ExecutionService {

  void doBaseWork(String message);

  void doAdvancedWork(String message, String requirements);
}
