package by.dma1979.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:16
 * @since : 2019.11
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "calculationRunner")
    private ApplicationRunner calculator;

    @MockBean(name = "booksInitializer")
    private ApplicationRunner booksInitializer;

    @org.junit.Test
    public void hello() throws Exception {
        for (String urlTemplate : Arrays.asList("/hi", "/hello")) {
            mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Chuck Norris")))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
        }
    }

    @org.junit.Test
    public void loginUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login?userName=Dzmitry"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                allOf(containsString("Enter login details"), containsString("Dzmitry"))))
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));

        mockMvc.perform(MockMvcRequestBuilders.post("/login?userName=Dzmitry"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Dzmitry is logged-in")))
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
    }

    @org.junit.Test
    public void gameOfThrones() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/got"))
            .andExpect(status().isOk())
            .andExpect(content().string(any(String.class)))
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
    }

    @org.junit.Test
    public void animal() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/animal"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Do you know")))
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
    }
}