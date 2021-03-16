package by.dma.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import by.dma.demo.model.Mentee;
import by.dma.demo.service.MenteeService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class MenteeResourceBaseClass {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenteeService menteeService;

    @BeforeEach
    public void before() throws Exception {
        RestAssuredMockMvc.mockMvc(mockMvc);
        // given:
        given(menteeService.getMenteeById(anyLong()))
            .willReturn(
                Mentee.builder().id(1L).name("Dzmitry").grade(90).build());

        // when:
        ResultActions resultActions = mockMvc.perform(get("/mentees/1"));

        // then:
        resultActions.andExpect(status().isOk())
                     .andExpect(jsonPath("id").value(1L))
                     .andExpect(jsonPath("name").value("Dzmitry"))
                     .andExpect(jsonPath("grade").value("90"));
    }
}
