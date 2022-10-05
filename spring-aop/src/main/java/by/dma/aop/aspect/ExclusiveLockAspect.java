package by.dma.aop.aspect;

import java.util.concurrent.locks.Lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExclusiveLockAspect {

  private final Lock lock;

  @Around("@annotation(by.dma.aop.annotation.ExclusiveLock)")
  public Object exclusiveLockAspect(ProceedingJoinPoint joinPoint)
          throws Throwable {

    if (lock.tryLock()) {
      try {
        return joinPoint.proceed();
      } finally {
        lock.unlock();
      }
    } else {
      log.warn("Method '{}' is locked", joinPoint.getSignature().getName());
      return null;
    }
  }

}
