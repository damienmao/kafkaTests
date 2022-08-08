package kafkaTestUsingEmbededKafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component

public class KafkaMyConsumer {


    private static final Logger Logger = LoggerFactory.getLogger(KafkaMyConsumer.class);

    private CountDownLatch latch;
    @KafkaListener(topics = "${kafkaTest.topic}")
    public void kafaListener (ConsumerRecord<String, String> consumerRecord) {
        Logger.info("message received {}", consumerRecord.toString());
    }

    private  void resetLatch (){
        latch = new CountDownLatch(1);
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }
}
