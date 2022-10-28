package by.dma;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import by.dma.aop.service.ExecutionServiceImpl;

@SpringBootApplication
public class SpringAopApplication {



   public static void main(String[] args) throws InterruptedException {
    var ctx = new SpringApplicationBuilder()
            .sources(SpringAopApplication.class)
            //.web(WebApplicationType.NONE)
            .web(WebApplicationType.SERVLET)
            .run(args);

    ExecutionServiceImpl executionService = ctx.getBean(ExecutionServiceImpl.class);
    executionService.doBaseWork("MESSAGE");
    executionService.doAdvancedWork("MESSAGE", "REQUIREMENTS");
    executionService.processWithResult("MESSAGE_WITH_RESULT");

    final Lock lock = ctx.getBean(Lock.class);
    try {
      lock.lock();
      final Thread thread = new Thread(
              () -> executionService.doExclusiveWork("MESSAGE"));
      thread.start();
      thread.join();
    } finally {
      lock.unlock();
    }
  }

/*
  public static void main(String[] args) {
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

@Controller
@ResponseBody
class TestController {

    @GetMapping("/")
    public String test() {
        return "It works on my machine";
    }

    @GetMapping("/random")
    public String random() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100));
    }
}

