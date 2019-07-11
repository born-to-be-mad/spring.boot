package by.dma.crudwebapp.controller;

import by.dma.crudwebapp.controller.business.CardService;
import by.dma.crudwebapp.controller.controller.CardController;
import by.dma.crudwebapp.controller.dto.CardRequestDTO;
import by.dma.crudwebapp.controller.model.Card;
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

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CardController.class)
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CardService service;

    @Captor
    ArgumentCaptor<CardRequestDTO> captor;

    @Test
    void requestNewCardCreationShouldCreateCardInDatabase() throws Exception {
        CardRequestDTO dto = new CardRequestDTO();
        dto.setDefinition("OOP");
        dto.setContent("Object Oriented Programming");
        dto.setAuthor("Vasya Pupkin");
        dto.setHashTag("#oop");

        long expectedCardId = 123L;
        when(service.createCard(captor.capture())).thenReturn(expectedCardId);

        mockMvc
            .perform(
                post("/api/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .content(mapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andExpect(header().exists("Location"))
            .andExpect(header().string("Location", "http://localhost/api/cards/" + expectedCardId));

        CardRequestDTO verifiedDTO = captor.getValue();
        assertThat(verifiedDTO.getDefinition(), is("OOP"));
        assertThat(verifiedDTO.getContent(), is("Object Oriented Programming"));
        assertThat(verifiedDTO.getHashTag(), is("#oop"));
    }

    @Test
    void updateKnownCardShouldUpdateCardInDatabase() throws Exception {
        CardRequestDTO requestDTO = new CardRequestDTO();
        requestDTO.setDefinition("OOP");
        requestDTO.setContent("Object Oriented Programming");
        requestDTO.setAuthor("Vasya Pupkin");
        requestDTO.setHashTag("#oop #programming #object");

        long cardId = 123L;
        when(service.updateCard(eq(cardId), captor.capture()))
                .thenReturn(createTestCard(101L, "OOP","Object Oriented Programming", "#oop #programming #object"));

        mockMvc
                .perform(
                        put("/api/cards/"+ cardId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk() )
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(cardId)))
                .andExpect(jsonPath("$.definition", is("OOP")))
                .andExpect(jsonPath("$.content", is("Object Oriented Programming")))
                .andExpect(jsonPath("$.hashTag", is("#oop #programming #object")));

        CardRequestDTO verifiedDTO = captor.getValue();
        assertThat(verifiedDTO.getDefinition(), is("OOP"));
        assertThat(verifiedDTO.getContent(), is("Object Oriented Programming"));
        assertThat(verifiedDTO.getHashTag(), is("#oop #programming #object"));
    }

    @Test
    void requestAllCardsShouldReturnSeveralCards() throws  Exception {
        when(service.getAllCards())
            .thenReturn(
                List.of(
                    createTestCard(101L, "OOP","Object Oriented Programming", "#oop #programming #object"),
                    createTestCard(102L, "AOP","Aspect Oriented Programming", "#aop #programming #aspect"),
                    createTestCard(103L, "TDD","Test Driven Development", "#tdd #programming #testing")
            ));

        mockMvc
            .perform(
                get("/api/cards"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].id", is(101)))
            .andExpect(jsonPath("$[0].definition", is("OOP")))
            .andExpect(jsonPath("$[0].content", is("Object Oriented Programming")))
            .andExpect(jsonPath("$[0].hashTag", is("#oop #programming #object")));

    }

    @Test
    void requestCardByIdShouldReturnCardFromDB() throws  Exception {
        long cardId = 101L;
        when(service.getCardById(cardId))
            .thenReturn(createTestCard(cardId, "OOP","Object Oriented Programming", "#oop #programming #object"));

        mockMvc
            .perform(
                get("/api/cards/101"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id", is(101)))
            .andExpect(jsonPath("$.definition", is("OOP")))
            .andExpect(jsonPath("$.content", is("Object Oriented Programming")))
            .andExpect(jsonPath("$.hashTag", is("#oop #programming #object")));
    }

    @Test
    void requestCardByUnknownIdShouldReturnNotFoundStatus() throws  Exception {
        long unknownCardId = 123L;
        when(service.getCardById(unknownCardId)).thenThrow(new CardNotFoundException("Card with id '123' not found"));

        mockMvc
            .perform(
                get("/api/cards/" + unknownCardId))
            .andExpect(status().isNotFound());

    }

    private Card createTestCard(long id, String definition, String content, String hashtag) {
        Card card = new Card();
        card.setId(id);
        card.setDefinition(definition);
        card.setContent(content);
        card.setHashTag(hashtag);
        card.setCreationDate(new Date());
        return card;
    }
}