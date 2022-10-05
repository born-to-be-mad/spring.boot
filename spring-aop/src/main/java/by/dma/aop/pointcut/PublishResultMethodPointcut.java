package by.dma.aop.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.annotation.AnnotationMethodMatcher;

import by.dma.aop.advice.PublishResultAfterReturnAdvice;
import by.dma.aop.annotation.PublishResult;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.10
 */
public class PublishResultMethodPointcut implements Pointcut {

  @Override
  public ClassFilter getClassFilter() {
    return ClassFilter.TRUE;
  }

  @Override
  public MethodMatcher getMethodMatcher() {
    return new AnnotationMethodMatcher(PublishResult.class, true) {

      @Override
      public boolean matches(Method method, Class<?> targetClass) {
        final boolean matches = super.matches(method, targetClass);

        if (matches) {
          if (method.getReturnType() == void.class || method.getReturnType() == Void.class) {
            throw new IllegalStateException(
                    String.format("You can not use @%s annotation on method " +
                                  "which return nothing",
                            PublishResultAfterReturnAdvice.class.getSimpleName()));
          }
        }

        return matches;
      }
    };
  }
}
