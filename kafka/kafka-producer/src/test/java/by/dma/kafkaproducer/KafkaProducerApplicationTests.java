package by.dma.kafkaproducer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@EmbeddedKafka(bootstrapServersProperty = "spring.kafka.bootstrap-servers",
topics = "pvtopic", brokerProperties = {"transaction.state.log.replication.factor=1"})
@SpringBootTest
@DirtiesContext
class KafkaProducerApplicationTests {

    @Test
    void contextLoads() {
    }

}
