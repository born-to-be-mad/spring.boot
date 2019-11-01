package by.dma1979.components;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:31
 * @since : 2019.10
 **/
@Component
public class Hello {
    @PostConstruct
    public void greet() {
        System.out.println("Hello World, from Spring Boot!");
    }
}
