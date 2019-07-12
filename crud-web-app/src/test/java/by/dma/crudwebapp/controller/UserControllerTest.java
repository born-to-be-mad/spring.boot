package by.dma.crudwebapp.controller;

import by.dma.crudwebapp.controller.business.UserService;
import by.dma.crudwebapp.controller.controller.UserController;
import by.dma.crudwebapp.controller.dto.UserRequestDTO;
import by.dma.crudwebapp.controller.exception.UserNotFoundException;
import by.dma.crudwebapp.controller.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService service;

    @Captor
    ArgumentCaptor<UserRequestDTO> captor;

    @Test
    void requestNewUserCreationShouldCreateUserInDatabase() throws Exception {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setLogin("dma");

        long expectedId = 123L;
        when(service.create(captor.capture())).thenReturn(expectedId);

        mockMvc
            .perform(post("/api/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8.name())
                    .content(mapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andExpect(header().exists("Location"))
            .andExpect(header().string("Location", "http://localhost/api/users/" + expectedId));

        UserRequestDTO verifiedDTO = captor.getValue();
        assertThat(verifiedDTO.getLogin(), is("dma"));
    }

    @Test
    void updateKnownUserShouldUpdateUserInDatabase() throws Exception {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setLogin("dma");

        long userId = 123L;
        when(service.update(eq(userId), captor.capture()))
                .thenReturn(createTestUser(userId, "dma"));

        mockMvc.perform(put("/api/users/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(123)))
                .andExpect(jsonPath("$.login", is("dma")));

        UserRequestDTO verifiedDTO = captor.getValue();
        assertThat(verifiedDTO.getLogin(), is("dma"));
    }

    @Test
    void updateUnknownUserShouldReturnNotFoundStatus() throws Exception {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setLogin("dma");

        long userId = 123L;
        when(service.update(eq(userId), captor.capture()))
                .thenThrow(new UserNotFoundException(String.format("User with id %s not found", userId)));

        mockMvc.perform(put("/api/users/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .content(mapper.writeValueAsString(requestDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void requestAllUsersShouldReturnSeveralUsers() throws  Exception {
        when(service.getAll())
            .thenReturn(
                List.of(
                        createTestUser(101L, "dma"),
                        createTestUser(102L, "mmm"),
                        createTestUser(103L, "rns")
            ));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(101)))
                .andExpect(jsonPath("$[0].login", is("dma")));

    }

    @Test
    void requestUserByIdShouldReturnUserFromDB() throws  Exception {
        long entityId = 101L;
        when(service.getById(entityId))
            .thenReturn(createTestUser(entityId, "dma"));

        mockMvc
            .perform(get("/api/users/101"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id", is(101)))
            .andExpect(jsonPath("$.login", is("dma")));
    }

    @Test
    void requestUserByUnknownIdShouldReturnNotFoundStatus() throws  Exception {
        long entityId = 123L;
        when(service.getById(entityId))
                .thenThrow(new UserNotFoundException(String.format("User with id %s' not found", entityId)));

        mockMvc
            .perform(get("/api/users/" + entityId))
            .andExpect(status().isNotFound());

    }

    @Test
    void deleteKnownUserByIdShouldDeleteCardFromDB() throws  Exception {
        mockMvc.perform(delete("/api/users/101")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteUnknownCardByIdShouldShouldReturnNotFoundStatus() throws  Exception {
        long entityId = 123L;

        doThrow(new UserNotFoundException(String.format("User with id %s not found", entityId)))
                .when(service).delete(entityId);

        mockMvc.perform(delete("/api/cards/" + entityId))
                .andExpect(status().isNotFound());
    }

    private User createTestUser(long id, String login) {
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        return user;
    }

}