package kafkaTestUsingEmbededKafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class KafkaMyProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger LOGGGER = LoggerFactory.getLogger(KafkaMyProducer.class);

    public void sendMessage(String topic, String payload) {
        LOGGGER.info("sending msg {} to kafka topic {}", payload, topic);
        kafkaTemplate.send(topic, payload);
    }
}
