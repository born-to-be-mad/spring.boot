package by.dma.kafkaproducer;

import java.util.Map;
import java.util.Random;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import static by.dma.kafkaproducer.KafkaProducerApplication.PAGE_VIEWS_TOPIC;

@SpringBootApplication
public class KafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    /* topic in Apache Kafka broker */
    static final String PAGE_VIEWS_TOPIC = "PVT";

}

@Configuration
class IntegrationConfiguration {

    @Bean
    IntegrationFlow flow(MessageChannel channel, KafkaTemplate<Object, Object> template) {
        var kafkaProducerMessageHandler = Kafka
                .outboundChannelAdapter(template)
                .topic(PAGE_VIEWS_TOPIC)
                .get();
        return IntegrationFlow
                .from(channel)
                .handle(kafkaProducerMessageHandler)
                .get();
    }

    @Bean
    MessageChannel channel() {
        return MessageChannels.direct().get();
    }
}

@Configuration
class RunnerConfiguration {

    void kafka(KafkaTemplate<Object, Object> template) {
        var pageView = random("kafka");
        template.send(PAGE_VIEWS_TOPIC, pageView);
    }

    void integration(MessageChannel channel) {
        var message = MessageBuilder
                .withPayload(random("integration"))
                //.copyHeaders(Map.of(KafkaHeaders.TOPIC, PAGE_VIEWS_TOPIC))
                .build();
        channel.send(message);
    }

    private PageView random(String source) {
        var towns = "Vilnius,Warsaw,Kiiv,Riga,Malaga,Gdansk,Berlin".split(",");
        var pages = "blog.html,about.html,contact.html,news.html,index.html".split(",");
        var random = new Random();
        return new PageView(
                pages[random.nextInt(pages.length)],
                Math.random() > .5 ? 100 : 1000,
                towns[random.nextInt(towns.length)],
                source
        );
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> runnerListener(
            KafkaTemplate<Object, Object> template,
            MessageChannel channel) {

        return event -> {
            kafka(template);
            integration(channel);
        };
    }
}

@Configuration
class KafkaConfiguration {

    @KafkaListener(topics = PAGE_VIEWS_TOPIC, groupId = "pv_topic_group")
    public void onNewPageView(Message<PageView> pageView) {
        System.out.println("-".repeat(50));
        System.out.println("New page view " + pageView.getPayload());
        pageView.getHeaders().forEach((s, o) -> System.out.println(s + '=' + o));
    }

    @Bean
    NewTopic pageViewsTopic() {
        return new NewTopic(PAGE_VIEWS_TOPIC, 1, (short) 1);
    }

    @Bean
    JsonMessageConverter jsonMessageConverter() {
        return new JsonMessageConverter();
    }

    @Bean
    KafkaTemplate<Object, Object> kafkaTemplate(ProducerFactory<Object, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory,
                Map.of(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class));
    }
}

record PageView(String page, long duration, String town, String source) {

}



