package by.dma.aop.service;

import org.springframework.stereotype.Service;

import by.dma.aop.annotation.ExclusiveLock;
import by.dma.aop.annotation.LogMethod;
import by.dma.aop.annotation.PublishResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    System.out.println(
            "ExecutionServiceImpl#doAdvancedWork... requires " + requirements);
  }

  @LogMethod
  @ExclusiveLock
  public void doExclusiveWork(String message) {
    System.out.println("ExecutionServiceImpl#doExclusiveWork..." + message);
  }

  @PublishResult
  public ResultDto processWithResult(String message) {
    doBaseWork(message);
    System.out.println("ExecutionServiceImpl#processWithResult...");
    return ResultDto.builder()
            .source(message)
            .description("Please, accept this message")
            .build();
  }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ResultDto {

  private String source;

  private String description;
}
