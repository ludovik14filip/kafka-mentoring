package org.epam.learn.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.epam.learn.model.Signal;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaSenderService {
    private final KafkaTemplate<String, Signal> kafkaTemplate;

    @SneakyThrows
    public Signal justSend(String topicName, Signal signal) {
        List<Signal> signals = new ArrayList<>();
        ProducerRecord<String, Signal> producerRecord =
                new ProducerRecord<>(topicName, String.valueOf(signal.getVehicleId()), signal);

        return kafkaTemplate.send(producerRecord).completable().thenApply(r -> r.getProducerRecord()).join().value();

    }
}
