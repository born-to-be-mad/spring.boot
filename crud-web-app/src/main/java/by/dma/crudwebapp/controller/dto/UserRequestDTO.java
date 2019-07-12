package by.dma.crudwebapp.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRequestDTO {
    @NotEmpty
    private String login;
}
