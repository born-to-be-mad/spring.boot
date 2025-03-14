package by.dma.crudwebapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRequestDTO {
    @NotEmpty
    private String login;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;
}
