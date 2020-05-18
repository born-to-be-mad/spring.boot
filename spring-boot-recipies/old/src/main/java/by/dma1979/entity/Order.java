package by.dma1979.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:33
 * @since : 2019.12
 **/
@Data
@Entity
public class Order {
    @Id
    private long id;
    private String number;
}
