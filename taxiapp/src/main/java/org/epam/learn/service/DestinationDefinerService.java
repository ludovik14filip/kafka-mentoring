package org.epam.learn.service;

import java.util.Random;

import org.epam.learn.model.Signal;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DestinationDefinerService {
    private final KafkaSenderService kafkaSenderService;

    public Signal defineDestinationAndLogResult(Signal in) {
        defineDestination(in);
        kafkaSenderService.justSend("logintopic", in);
        return in;
    }

    private void defineDestination(Signal in) {
        int destinationCoordinate = new Random().nextInt(in.getCoordinate() - 1) + 1;
        in.setDestination(destinationCoordinate);
        in.setDetails("Distance to the target: " + (in.getCoordinate() - in.getDestination()) + "Start info: " +
                in.getDetails());
    }
}
