package org.epam.learn.service;

import org.epam.learn.model.Signal;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogAndSaveService {

    public void logAndSaveSignalInfo(Signal in) {
        saveInDataBase(in);
        logCurrentInfo(in);
        log.info(in.toString());
    }

    private void logCurrentInfo(Signal in) {
        log.info("Signal logged : " + in.toString());
    }

    private void saveInDataBase(Signal in) {
        log.info("Signal saved in database: " + in.toString());
    }

}
