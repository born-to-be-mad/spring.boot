package by.dma.aop.aspect;

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
}
