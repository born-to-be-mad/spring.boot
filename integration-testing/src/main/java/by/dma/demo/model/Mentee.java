package by.dma.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Builder
public class Mentee {

  @Id @GeneratedValue
  private Long id;

  private String name;

  private boolean active;

  private int grade;

  public Mentee(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
