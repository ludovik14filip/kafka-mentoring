package org.epam.learn.service;

import org.epam.learn.model.Signal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaxiService {
    private final KafkaSenderService kafkaSenderService;
    private final ValidatorService validatorService;

    @Value("${tpd.topic-name}")
    private String topicName;

    public Signal processSignal(Signal signal) {
        Signal validSignal = validatorService.validateSignal(signal);
        return kafkaSenderService.justSend(topicName, validSignal);
    }
}
