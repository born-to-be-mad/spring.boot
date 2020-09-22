package by.dma.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:26
 * @since : 2020.07
 **/
@Entity
@Data
@NoArgsConstructor
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  public Customer(String name, String email) {
    this.name = name;
    this.email = email;
  }
}
