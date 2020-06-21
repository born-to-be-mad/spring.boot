package by.dma.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
