package by.dma.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:53
 * @since : 2020.05
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(CompletableFutureController.class)
public class CompletableFutureControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @org.junit.Test
  public void hello() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/completable-future"))
                                 .andExpect(request().asyncStarted())
                                 .andDo(MockMvcResultHandlers.log())
                                 .andReturn();
    mockMvc.perform(asyncDispatch(mvcResult))
           .andExpect(status().isOk())
           .andExpect(content().contentTypeCompatibleWith(TEXT_PLAIN))
           .andExpect(content().string("Hello World, from CompletableFuture!"));

  }
}
