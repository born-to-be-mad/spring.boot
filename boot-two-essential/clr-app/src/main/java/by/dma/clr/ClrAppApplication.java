package by.dma.clr;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import by.dma.clr.model.Room;

@SpringBootApplication
@Profile("!fizzbuzz")
public class ClrAppApplication {

    private static final Logger LOG = LoggerFactory.getLogger(ClrAppApplication.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            LOG.info("Starting CLR application");

            LOG.info("### ROOMS ###");
            ResponseEntity<List<Room>> rooms = restTemplate.exchange(
                    "http://localhost:8080/api/rooms",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() { //https://bugs.openjdk.java.net/browse/JDK-8212586

                    });
            rooms.getBody()
                 .forEach(room -> LOG.info(room.toString()));


/*            LOG.info("### STAFF ###");
            ResponseEntity<List<StaffMember>> staff = restTemplate.exchange(
                "http://localhost:8080/api/staff", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<StaffMember>>() { //https://bugs.openjdk.java.net/browse/JDK-8212586
                });
            rooms.getBody()
                 .forEach(member -> LOG.info(member.toString()));*/

            LOG.info("Finishing CLR application");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ClrAppApplication.class, args);
    }

}
