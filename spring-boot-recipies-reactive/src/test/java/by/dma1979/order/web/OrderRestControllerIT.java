package by.dma1979.order.web;

import by.dma1979.order.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:05
 * @since : 2019.11
 **/
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureWebTestClient
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderRestControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void listOrders() {
        webTestClient.get().uri("/order")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Order.class).hasSize(25);
    }

    @Test
    public void addAndGetOrder() {
        var order = new Order("test1", BigDecimal.valueOf(1234.56));
        webTestClient.post().uri("/order").bodyValue(order)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class).isEqualTo(order);
        webTestClient.get().uri("/order/{id}", order.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class).isEqualTo(order);

    }
}