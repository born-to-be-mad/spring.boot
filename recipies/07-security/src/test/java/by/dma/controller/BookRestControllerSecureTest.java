package by.dma.controller;

import java.util.Arrays;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import by.dma.entity.Book;
import by.dma.service.BookService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * @author : Dzmitry Marudau
 * @created at : 00:37
 * @since : 2020.07
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(BookRestController.class)
@WithMockUser
public class BookRestControllerSecureTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookService bookService;

  @Test
  public void shouldReturnListOfBooks() throws Exception {

    when(bookService.findAll()).thenReturn(Arrays.asList(
            new Book("123", "Spring 5 Recipes", "Marten Deinum", "Josh Long"),
            new Book("321", "Pro Spring MVC", "Marten Deinum", "Colin Yates")));

    mockMvc.perform(get("/books"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].isbn", Matchers.containsInAnyOrder("123", "321")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].title", Matchers.containsInAnyOrder("Spring 5 Recipes", "Pro Spring MVC")));
  }

  @Test
  public void shouldReturn404WhenBookNotFound() throws Exception {

    when(bookService.find(anyString())).thenReturn(Optional.empty());

    mockMvc.perform(get("/books/123"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void shouldReturnBookWhenFound() throws Exception {

    when(bookService.find(anyString())).thenReturn(
            Optional.of(new Book("123", "Spring 5 Recipes", "Marten Deinum", "Josh Long")));

    mockMvc.perform(get("/books/123"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", Matchers.equalTo("123")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("Spring 5 Recipes")));
  }

  @Test
  public void shouldAddBook() throws Exception {

    when(bookService.create(any(Book.class)))
            .thenReturn(
                    new Book("123456789", "Test Book Stored", "T. Author"));

    mockMvc.perform(post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"isbn\" : \"123456789\"}, \"title\" : \"Test Book\", \"authors\" : [\"T. Author\"]")
            .with(csrf()))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "http://localhost/books/123456789"));
  }
}


