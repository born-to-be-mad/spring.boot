package by.dma.crudwebapp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Table(name="user")
public class CardRequestDTO {
    @NotEmpty
    @Length(max = 50)
    private String hashTag;

    @NotEmpty
    @Length(max = 50)
    private String definition;

    @NotEmpty
    @Length(max = 255)
    private String content;

    @Length(max = 255)
    private String author;

    private Date creationDate;
}
