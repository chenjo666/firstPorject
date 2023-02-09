package com.nowcoder.community;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NowcoderApplication.class)
public class KafkaTests {

    @Autowired
    private KafkaProducer kafkaProducer;
    @Test
    public void test() {
        kafkaProducer.sendMsg("test", "where");
        kafkaProducer.sendMsg("test", "are");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

@Component
class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMsg(String topic, String msg) {
        kafkaTemplate.send(topic, msg);
    }
}

@Component
class KafkaConsumer {
    @KafkaListener(topics = "test")
    public void recvMsg(ConsumerRecord record) {
        System.out.println(record.value());
    }
}
