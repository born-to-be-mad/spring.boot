package by.dma.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.context.ApplicationEventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.10
 */
@RequiredArgsConstructor
@Slf4j
public class PublishResultAfterReturnAdvice implements AfterReturningAdvice {
  private final ApplicationEventPublisher eventPublisher;

  @Override
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
    if (returnValue != null) {
      log.info("Publishing result: {}", returnValue);
      eventPublisher.publishEvent(returnValue);
    }
  }
}
