package by.dma.circuitbreaker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.10
 */
@RestController
@RequestMapping("/api/delay")
@Slf4j
public class DelayController {

    private static final String CIRCUIT_BREAKER_INSTANCE_NAME = "delayService";

    private static final String FALLBACK_METHOD = "fallback";

    @GetMapping("/{ms}")
    @CircuitBreaker(name = CIRCUIT_BREAKER_INSTANCE_NAME, fallbackMethod = FALLBACK_METHOD)
    public String delay(@PathVariable int ms) throws InterruptedException {
        log.debug("Delaying for {} ms", ms);
        Thread.sleep(ms);
        return "Delayed for " + ms + " ms";
    }

    private String fallback(Exception e) {
        log.error("There is a problem at service. Use fallback");
        return "Fallback response";
    }

}
