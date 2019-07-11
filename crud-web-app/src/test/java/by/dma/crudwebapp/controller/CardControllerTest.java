package by.dma.crudwebapp.controller;

import by.dma.crudwebapp.controller.business.CardService;
import by.dma.crudwebapp.controller.controller.CardController;
import by.dma.crudwebapp.controller.dto.CardRequestDTO;
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

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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
    void requestNewBookCreationShouldCreteBookInDB() throws Exception {
        CardRequestDTO dto = new CardRequestDTO();
        dto.setDefinition("WTF");
        dto.setContent("What The Fuck");
        dto.setAuthor("Vasya Pupkin");
        dto.setHashTag("#wtf");

        when(service.createCard(captor.capture())).thenReturn(123L);

        mockMvc.perform(
                post("/api/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/api/cards/123"));

        CardRequestDTO verifiedDTO = captor.getValue();
        assertThat(verifiedDTO.getDefinition(), is("WTF"));
        assertThat(verifiedDTO.getContent(), is("What The Fuck"));
        assertThat(verifiedDTO.getHashTag(), is("#wtf"));
    }
}