package by.dma.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for {@link CurrencyController}.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class CurrencyControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void whenCallCurrencyWithSpanishLocaleThenReturnProperCurrency()
          throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/currency")
                                          .header("Accept-Language", "es-ES")
                                          .param("amount", "10032.5"))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString("10.032,50 \u20AC")));
  }

  @Test
  public void whenCallCurrencyWithUSALocaleThenReturnProperCurrency()
          throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/currency")
                                          .header("Accept-Language", "en-US")
                                          .param("amount", "10032.5"))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString("$10,032.50")));
  }

  @Test
  public void whenCallCurrencyWithRomanianLocaleWithArraysThenReturnLocaleCurrencies()
          throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/currency")
                                  .header("Accept-Language", "ro-RO")
                                  .param("amountList",
                                         "10", "20", "30"))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString(
                   "[10,00 RON, 20,00 RON, 30,00 RON]")));
  }

  @Test
  public void whenCallCurrencyWithUSALocaleWithoutDecimalThenReturnCurrencyWithoutTrailingZeros()
          throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/currency")
                                  .header("Accept-Language", "en-US")
                                  .param("amount", "10032"))
           .andExpect(status().isOk())
            .andExpect(content().string(containsString("$10,032")));
  }

  @Test
  public void whenCallCurrencyWithUSALocaleThenReturnReplacedDecimalPoint()
          throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/currency")
                                  .header("Accept-Language", "en-US")
                                  .param("amount", "1.5"))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString("1,5")));
  }
}
