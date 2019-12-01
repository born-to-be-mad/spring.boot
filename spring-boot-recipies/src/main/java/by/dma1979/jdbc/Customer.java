package by.dma1979.jdbc;

import lombok.Data;

/**
 * @author : Dzmitry Marudau
 * @created at : 15:50
 * @since : 2019.12
 **/
@Data
public class Customer {
    private final long id;
    private final String name;
    private final String email;
}
