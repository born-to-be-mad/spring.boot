package by.dma.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class CurrencyControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void whenCallCurrencyWithSpanishLocale_ThenReturnProperCurrency() throws Exception {
    mockMvc.perform(get("/currency")
                        .header("Accept-Language", "es-ES")
                        .param("amount", "10032.5"))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString("¤10,032.50")));
  }

  @Test
  public void whenCallCurrencyWithUSALocale_ThenReturnProperCurrency() throws Exception {
    mockMvc.perform(get("/currency")
                        .header("Accept-Language", "en-US")
                        .param("amount", "10032.5"))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString("¤10,032.50")));
  }

  @Test
  public void whenCallCurrencyWithRomanianLocaleWithArrays_ThenReturnLocaleCurrencies() throws Exception {
    mockMvc.perform(get("/currency")
                        .header("Accept-Language", "en-GB")
                        .param("amountList", "10", "20", "30"))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString("¤10.00, ¤20.00, ¤30.00")));
  }

  @Test
  public void whenCallCurrencyWithUSALocaleWithoutDecimal_ThenReturnCurrencyWithoutTrailingZeros() throws Exception {
    mockMvc.perform(get("/currency")
                        .header("Accept-Language", "en-US")
                        .param("amount", "10032"))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString("¤10,032")));
  }

  @Test
  public void whenCallCurrencyWithUSALocale_ThenReturnReplacedDecimalPoint() throws Exception {
    mockMvc.perform(get("/currency")
                        .header("Accept-Language", "en-US")
                        .param("amount", "1.5"))
           .andExpect(status().isOk())
           .andExpect(content().string(containsString("1,5")));
  }
}
