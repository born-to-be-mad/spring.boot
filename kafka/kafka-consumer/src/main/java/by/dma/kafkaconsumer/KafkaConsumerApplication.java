package by.dma.kafkaconsumer;

import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

    @Bean
    Function<KStream<String, PageView>, KStream<String, Long>> myConsumer() {
        return pageViews -> pageViews
                .filter((str, pageView) -> pageView.duration() > 100)
                .map((str, pageView) -> new KeyValue<>(pageView.page(), 0L))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
                .count(Materialized.as("pcmv"))
                .toStream();
    }

    @Bean
    Consumer<KTable<String, Long>> logger() {
        return table -> table.toStream()
                .foreach((p, count) -> System.out.println("page: " + p + ", count:" + count));
    }
}

record PageView(String page, long duration, String town, String source) {

}