package by.dma.securityjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import by.dma.securityjpa.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories
public class SpringSecurityJpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityJpaApplication.class, args);
  }

}
