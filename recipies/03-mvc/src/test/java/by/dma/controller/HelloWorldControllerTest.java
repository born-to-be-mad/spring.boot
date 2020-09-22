package by.dma.controller;

import by.dma.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:35
 * @since : 2020.05
 **/
@RunWith(SpringRunner.class)
@MockBeans({
        @MockBean(BookService.class)
})
@WebMvcTest(HelloWorldController.class)
/*
* The @WebMvcTest instructs the Spring Test framework to set up an application context
* for testing this specific controller.
* It will start a minimal Spring Boot application with only
* `the web-related beans like @Controller, @ControllerAdvice, etc.
 */
class HelloWorldControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void hello() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/hw"))
           .andExpect(status().isOk())
           .andExpect(content().string("Hello World from Spring Boot!"))
           .andExpect(content().contentTypeCompatibleWith(
                   MediaType.TEXT_PLAIN));
  }
}
