package by.dma.room;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RoomServicesApplication {

  public static void main(String[] args) {
    SpringApplication.run(RoomServicesApplication.class, args);
  }

}
