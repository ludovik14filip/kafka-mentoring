package org.epam.learn.controller;

import org.epam.learn.converter.AppMapperr;
import org.epam.learn.model.Signal;
import org.epam.learn.model.SignalRequest;
import org.epam.learn.service.TaxiService;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class TaxiController {
    private final AppMapperr appMapperr;
    private final TaxiService taxiService;

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Signal> getSignal(@RequestBody SignalRequest request) {
        Signal signal = appMapperr.convert(request);
        return ResponseEntity.ok(taxiService.processSignal(signal));
    }

}
