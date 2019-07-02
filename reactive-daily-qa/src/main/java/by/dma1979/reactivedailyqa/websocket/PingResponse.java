package by.dma1979.reactivedailyqa.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dzmitry.marudau
 * @since 2019.6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PingResponse {
    private String message;
}
