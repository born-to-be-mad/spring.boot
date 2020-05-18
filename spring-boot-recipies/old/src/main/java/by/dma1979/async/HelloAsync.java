package by.dma1979.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:36
 * @since : 2019.12
 **/
@Component
public class HelloAsync {
    private static final Logger logger = LoggerFactory.getLogger(HelloAsync.class);

    @Async
    public void printMessage() throws InterruptedException {
        System.out.println("printMessage...");
        Thread.sleep(1_000L);
        logger.info("Hello World, from Asynchronous Spring Boot 2!");
        System.out.println("printMessage...finished!");
    }

    @Async
    public void asyncMethodWithVoidReturnType() {
        System.out.println("Execute method asynchronously. "
                + Thread.currentThread().getName());
    }
}

