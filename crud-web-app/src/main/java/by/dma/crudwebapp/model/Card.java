package by.dma.crudwebapp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Entity to manage cards.
 *
 * @author dzmitry.marudau
 * @since 2019.7
 */
@Data
@Entity
public class Card {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50)
    private String hashTag;
    @Column(nullable = false, length = 50)
    private String definition;

    @Column(nullable = false)
    private String content;

    @Column
    private String author;

    @Column
    private Date creationDate;

}
