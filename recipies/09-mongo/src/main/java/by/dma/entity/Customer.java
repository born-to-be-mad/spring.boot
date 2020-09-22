package by.dma.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:22
 * @since : 2020.07
 **/
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Customer {
  private String id;
  private final String name;
  private final String email;

  public Customer(String name, String email) {
    this.name = name;
    this.email = email;
  }
}
