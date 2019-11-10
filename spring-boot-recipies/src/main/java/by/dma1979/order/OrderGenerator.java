package by.dma1979.order;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Simple component for creating Order instances.
 * @author : Dzmitry Marudau
 * @created at : 00:26
 * @since : 2019.11
 **/
public class OrderGenerator {
    public Order generate() {
        var amount = ThreadLocalRandom.current().nextDouble(1000.00);
        return new Order(UUID.randomUUID().toString(), BigDecimal.valueOf(amount));
    }
}
