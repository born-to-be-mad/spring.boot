package by.dma.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:09
 * @since : 2020.05
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public final class Order {
  private String id;
  private BigDecimal amount;
}
