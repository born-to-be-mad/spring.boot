package by.dma1979.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dzmitry.marudau
 * @since 2019.6
 */
@Controller
public class IndexController {

    @GetMapping
    public String index() {
        return "index";
    }
}
