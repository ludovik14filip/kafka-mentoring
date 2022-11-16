package org.example.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class ListenersConfig {

    private final SignalStringDataMapper signalStringDataMapper;

    @KafkaListener(topics = "taxi")
    public void listenasString (String in) throws JsonProcessingException {
        Signal signal = signalStringDataMapper.parseStringToSignal(in);
        log.info("Signal was catch" + signal.toString());
        defineDestination(signal);
    }

    private void defineDestination(Signal in) {
        int destinationCoordinate = new Random().nextInt(in.getCoordinate() - 1) + 1;
        in.setDestination(destinationCoordinate);
        in.setDetails("Distance to the target: " + (in.getCoordinate() - in.getDestination()) + "Start info: " +
                in.getDetails());
    }

}
