package by.dma.aop.aspect;

import java.lang.reflect.Method;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;

import by.dma.aop.annotation.LogMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class LoggingAspect {

  /* @Pointcut("execution(* by.dma.aop.service.ExecutionServiceImpl.*(..))")
  public void executionServiceMethodsPointcut() {
  } */

  //@Around("executionServiceMethodsPointcut()")
  // public Object logMethodExecution(ProceedingJoinPoint joinPoint,) throws Throwable {

  @Around("@annotation(logMethod)")
  public Object logMethodExecution(ProceedingJoinPoint joinPoint,
          LogMethod logMethod) throws Throwable {

    final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    final Method method = signature.getMethod();
    final StopWatch stopWatch = new StopWatch();

    try {
      if (logMethod.logArguments()) {
        final String arguments = getMethodArguments(
                signature.getParameterNames(), joinPoint.getArgs());
        log.info("Method '{}' is called with arguments: {}",
                method, arguments);
      } else {
        log.info("Method '{}' is called", method);
      }

      stopWatch.start();
      var result = joinPoint.proceed();
      stopWatch.stop();

      log.info("Finish execution of method [{}] (running {} ns)",
              method, stopWatch.getTotalTimeNanos());
      return result;
    } catch (Exception e) {
      stopWatch.stop();
      log.error("Fail method {} execution (running {} ns)",
              method, stopWatch.getTotalTimeNanos());
      throw e;
    }

  }

  private String getMethodArguments(String[] parameterNames, Object[] args) {
    return IntStream.iterate(0, i -> i + 1)
            .limit(Math.min(parameterNames.length, args.length))
            .mapToObj(i -> parameterNames[i] + " = " + args[i])
            .collect(Collectors.joining(", "));
  }
}
