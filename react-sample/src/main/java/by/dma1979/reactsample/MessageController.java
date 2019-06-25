package by.dma1979.reactsample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
@RestController
public class MessageController {
    //Example: http://localhost:8888/apply?action=Hi&destination=Dimas
    @RequestMapping("/apply")
    public Message greeting(@RequestParam(value = "action", defaultValue = "Hello") String action,
                            @RequestParam(value = "destination", defaultValue = "World") String destination) {
        return new Message(action, destination);
    }

}
