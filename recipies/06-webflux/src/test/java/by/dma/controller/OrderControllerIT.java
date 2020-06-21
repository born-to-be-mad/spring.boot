package by.dma.controller;

import by.dma.model.Order;

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
 * TEst for {@link OrderController}.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureWebTestClient
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
/* @DirtiesContext is needed here because the OrderService is a stateful bean.
  So after adding an Order to the collection, it needs to be reset for the
  next test */
public class OrderControllerIT {

  @Autowired
  private WebTestClient webTestClient;

  @Test(expected = IllegalStateException.class)
  public void listOrders() {
    //Its is blocking operation, so timeout is expected
    webTestClient.get()
            .uri("/orders")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Order.class).hasSize(25);
  }

  @Test
  public void listOrdersAsEvent() {
    webTestClient.get()
            .uri("/orders/as-event")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Order.class).hasSize(25);
  }

  @Test
  public void addAndGetOrder() {
    var order = new Order("test1", BigDecimal.valueOf(1234.56));
    webTestClient.post().uri("/orders")
            .bodyValue(order)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Order.class).isEqualTo(order);
    webTestClient.get().uri("/orders/{id}", order.getId())
            .exchange()
            .expectStatus().isOk()
            .expectBody(Order.class).isEqualTo(order);
  }
}
