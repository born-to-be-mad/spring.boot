package by.dma1979.reactsample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String action;

    private String destination;
}
