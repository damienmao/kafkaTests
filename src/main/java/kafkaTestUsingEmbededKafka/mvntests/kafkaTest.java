package kafkaTestUsingEmbededKafka.mvntests;

import kafkaTestUsingEmbededKafka.consumer.KafkaMyConsumer;
import kafkaTestUsingEmbededKafka.producer.KafkaMyProducer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@EmbeddedKafka(partitions = 3, brokerProperties = {"listeners = PLAINTEXT://localhost:9092", "port=9092"})
public class kafkaTest {

    @Autowired
    private KafkaMyProducer kafkaMyProducer;

    @Autowired
    private KafkaMyConsumer kafkaMyConsumer;


    @Value("${kafkaTest.test}")
    private String topic;

@Test
    public void verifyMsgConsumed() throws InterruptedException {
        String data = "my kafka skill is amazing";
        kafkaMyProducer.sendMessage(topic, data);

        boolean messageConsumed = kafkaMyConsumer.getLatch().await(10, TimeUnit.SECONDS);
        Assertions.assertEquals(messageConsumed,true);

    }
}
