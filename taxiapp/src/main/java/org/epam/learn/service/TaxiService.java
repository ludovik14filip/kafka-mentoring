package org.epam.learn.service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

import org.epam.learn.model.Signal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.SendResult;
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

    private <T> Function<T, Signal> softExecution(Supplier<T> func) {
        return arg -> {
            CompletableFuture<SendResult<String, Signal>> future =
                    (CompletableFuture<SendResult<String, Signal>>) func.get();

            return future.thenApply(r -> r.getProducerRecord().value()).join();
        };
    }

}
