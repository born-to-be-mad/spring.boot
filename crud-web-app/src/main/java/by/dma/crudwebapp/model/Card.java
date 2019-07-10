package by.dma.crudwebapp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author dzmitry.marudau
 * @since 2019.7
 */
@Data
@Entity
public class Card {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String hashTag;

    @Column(nullable = false)
    private String definition;

    @Column(nullable = false)
    private String content;

    @Column
    private String author;

    @Column
    private Date creationDate;

}
