package by.dma1979.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:23
 * @since : 2019.11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Order {
    private String id;
    private BigDecimal amount;


}
