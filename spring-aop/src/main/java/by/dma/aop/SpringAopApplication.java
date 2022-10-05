package by.dma.aop;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import by.dma.aop.service.ClassicLoggingExecutionService;
import by.dma.aop.service.ExecutionService;

@SpringBootApplication
public class SpringAopApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringAopApplication.class, args);
  }

  @Bean
  ApplicationListener<ApplicationReadyEvent> applicationListener(
          @Qualifier("decoratedClassicLoggingService")
          ExecutionService executionService) {

    return event -> {
      System.out.println("OnApplicationReadyEvent processing....");
      var app = event.getSpringApplication();
      System.out.println(
              "SpringApplication.webApplicationType: " + app.getWebApplicationType());

      executionService.doBaseWork("MESSAGE");
      executionService.doAdvancedWork("MESSAGE", "REQUIREMENTS");
    };
  }
}
