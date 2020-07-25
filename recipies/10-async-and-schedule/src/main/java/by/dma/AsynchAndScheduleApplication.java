package by.dma;

import java.io.IOException;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:24
 * @since : 2020.05
 **/
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class AsynchAndScheduleApplication {

  public static void main(String[] args) throws IOException {
    SpringApplication.run(AsynchAndScheduleApplication.class, args);

    System.out.println("Press [ENTER] to quit:");
    System.in.read();
  }

  @Bean
  public TaskExecutor taskExecutor(TaskExecutorBuilder builder) {
    return builder.corePoolSize(4)
                  .maxPoolSize(16)
                  .queueCapacity(125)
                  .threadNamePrefix("dma-exec-")
                  .build();
  }

  @Bean
  public ApplicationRunner startupRunner(AsynchCalculator calc) {
    return (args) -> calc.calculate();
  }

}
