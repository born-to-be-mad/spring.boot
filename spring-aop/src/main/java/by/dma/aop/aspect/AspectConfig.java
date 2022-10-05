package by.dma.aop.aspect;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import by.dma.aop.advice.PublishResultAfterReturnAdvice;
import by.dma.aop.pointcut.PublishResultMethodPointcut;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.10
 */
@Configuration(proxyBeanMethods = false)
public class AspectConfig {

  @Bean
  public LoggingAspect loggingAspect() {
    return new LoggingAspect();
  }

  @Bean
  public ExclusiveLockAspect exclusiveLockAspect(Lock lock) {
    return new ExclusiveLockAspect(lock);
  }

  @Bean
  public Lock lock() {
    return new ReentrantLock();
  }

  @Bean
  @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
  public PointcutAdvisor publishResultPointcutAdvisor(
          ApplicationEventPublisher eventPublisher) {

    return new DefaultPointcutAdvisor(new PublishResultMethodPointcut(),
            new PublishResultAfterReturnAdvice(eventPublisher));
  }
}
