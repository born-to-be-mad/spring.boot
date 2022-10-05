package by.dma.aop.aspect;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
