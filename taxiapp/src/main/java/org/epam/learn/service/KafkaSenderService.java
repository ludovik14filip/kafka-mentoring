package org.epam.learn.service;

import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.epam.learn.model.Signal;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaSenderService {
    private final KafkaTemplate<String, Signal> kafkaTemplate;

    @Transactional
    @SneakyThrows
    public Signal justSend(String topicName, Signal signal) {
        ProducerRecord<String, Signal> producerRecord =
                new ProducerRecord<>(topicName, String.valueOf(signal.getVehicleId()), signal);
        return kafkaTemplate.send(producerRecord).get(5000, TimeUnit.SECONDS).getProducerRecord().value();
    }
}
