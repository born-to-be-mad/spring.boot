package by.dma.crudwebapp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CardRequestDTO {
    @NotEmpty
    @Length(max = 21)
    private String hashTag;

    @NotEmpty
    @Length(max = 50)
    private String definition;

    @Length(max = 255)
    private String content;

    @Length(max = 50)
    private String author;

    @NotNull
    private Date creationDate;
}
