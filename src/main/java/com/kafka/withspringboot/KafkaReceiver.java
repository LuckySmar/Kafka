package com.kafka.withspringboot;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * @ClassName KafkaReceiver
 * @Author ZhangY
 * @Date 2020/02/18 17:16
 * @Version 1.0.0
 * @Description 消息接收者
 */
@Component
@Slf4j
public class KafkaReceiver {

    @KafkaListener(topics = {"topic"})
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

            log.info("----------------- record =" + record);
            log.info("------------------ message =" + message);
        }

    }

}
