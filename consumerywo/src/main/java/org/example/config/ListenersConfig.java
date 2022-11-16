package org.example.config;

import java.util.Random;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ListenersConfig {
    private final Random random = new Random();
    private final SignalStringDataMapper signalStringDataMapper;

    @KafkaListener(topics = "taxi", containerFactory = "containerFactory")
    @SendTo("logintopic")
    public String listenasString(String in) throws JsonProcessingException {
        Signal signal = signalStringDataMapper.parseStringToSignal(in);
        defineDestination(signal);
        log.info("Signal was catch" + signal);
        return signalStringDataMapper.parseSignalToString(signal);
    }

    private void defineDestination(Signal in) {
        int destinationCoordinate = random.nextInt(in.getCoordinate() - 1) + 1;
        in.setDestination(destinationCoordinate);
        in.setDetails("Distance to the target: " + (in.getCoordinate() - in.getDestination()) + "Start info: " +
                in.getDetails());
    }

}
