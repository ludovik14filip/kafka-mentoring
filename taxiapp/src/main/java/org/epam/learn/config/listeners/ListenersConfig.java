package org.epam.learn.config.listeners;

import org.epam.learn.model.Signal;
import org.epam.learn.service.DestinationDefinerService;
import org.epam.learn.service.LogAndSaveService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ListenersConfig {

    private final DestinationDefinerService destinationDefinerService;
    private final LogAndSaveService logAndSaveService;

    @KafkaListener(topics = "taxi", containerFactory = "containerFactory")
    public void taxiSignalListener(Signal signal) {
        log.info("Signal was catch" + signal.toString());
        destinationDefinerService.defineDestinationAndLogResult(signal);
    }

    @KafkaListener(topics = "logintopic", containerFactory = "containerFactory")
    public void logSignalListener(Signal signal) {
        log.info("Signal to log was catch" + signal.toString());
        logAndSaveService.logAndSaveSignalInfo(signal);
    }
}
