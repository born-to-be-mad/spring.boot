package by.dma.aop;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import by.dma.aop.service.ExecutionService;
import by.dma.aop.service.ExecutionServiceImpl;

@SpringBootApplication
public class SpringAopApplication {

  public static void main(String[] args) {
    var ctx = new SpringApplicationBuilder()
            .sources(SpringAopApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    ExecutionService executionService = ctx.getBean(ExecutionServiceImpl.class);
    executionService.doBaseWork("MESSAGE");
    executionService.doAdvancedWork("MESSAGE", "REQUIREMENTS");
  }

/*   public static void main(String[] args) {
    SpringApplication.run(SpringAopApplication.class, args);
  }

  @Bean
  ApplicationListener<ApplicationReadyEvent> applicationListener(
          @Qualifier("executionServiceImpl")
          ExecutionService executionService) {

    return event -> {
      System.out.println("OnApplicationReadyEvent processing....");
      var app = event.getSpringApplication();
      System.out.println(
              "SpringApplication.webApplicationType: " + app.getWebApplicationType());

      executionService.doBaseWork("MESSAGE");
      executionService.doAdvancedWork("MESSAGE", "REQUIREMENTS");
    };
  } */
}
