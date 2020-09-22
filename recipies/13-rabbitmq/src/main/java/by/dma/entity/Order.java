package by.dma.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:54
 * @since : 2020.07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  private String id;
  private BigDecimal amount;
}
