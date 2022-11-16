package org.epam.learn.service;

import java.util.Random;

import org.epam.learn.model.Signal;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {
    private final Random random = new Random();

    public Signal validateSignal(Signal signal) {
        signal.setVehicleId(random.nextLong(0L, Long.MAX_VALUE));
        insideValidator(signal);
        return signal;
    }

    private Integer random() {
        return random.nextInt() * 10001;
    }

    private void insideValidator(Signal coordinate) {
        if (coordinate.getCoordinate() == null || coordinate.getCoordinate() < 0 ||
                coordinate.getCoordinate() > 10001) {
            coordinate.setCoordinate(random());
        }
    }


}
