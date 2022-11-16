package org.epam.learn.converter;

import org.epam.learn.model.Signal;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SignalStringDataMapper {
    public Signal parseStringToSignal(String in) {
        Signal signal = null;
        try {
            signal = new ObjectMapper().readValue(in, Signal.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return signal;
    }

    public String parseSignalToString(Signal in) {
        try {
            return new ObjectMapper().writeValueAsString(in);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
