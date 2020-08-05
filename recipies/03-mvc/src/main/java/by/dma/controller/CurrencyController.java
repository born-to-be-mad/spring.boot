package by.dma.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Currency controller.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
@Controller
public class CurrencyController {

  @GetMapping(value = "/currency")
  public String exchange(
          @RequestParam(value = "amount", required = false) String amount,
          @RequestParam(value = "amountList", required = false) List amountList,
          Locale locale) {

    return "currencies/currencies";
  }
}
