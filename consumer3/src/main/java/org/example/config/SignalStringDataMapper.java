package org.example.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Slf4j
public class SignalStringDataMapper {
    public Signal parseStringToSignal(String in) {
        Signal signal = null;
        try {
            signal = new ObjectMapper().readValue(in, Signal.class);
        } catch (JsonProcessingException e) {
          log.error(e.getMessage());
        }
        return signal;
    }

    public String parseSignalToString(Signal in) {
        String out = in.toString();
        try {
            out = new ObjectMapper().writeValueAsString(in);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return out;
    }
}
