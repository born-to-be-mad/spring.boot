package by.dma.components;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:31
 * @since : 2019.10
 **/
@Component
public class HelloWorld {
    @PostConstruct
    public void greet() {
        System.out.println("Hello World, from Spring Boot!");
    }
}
