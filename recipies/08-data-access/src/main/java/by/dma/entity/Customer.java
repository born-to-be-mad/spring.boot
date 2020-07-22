package by.dma.entity;

import lombok.Data;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:22
 * @since : 2020.07
 **/
@Data
public class Customer {
  private final long id;
  private final String name;
  private final String email;
}
