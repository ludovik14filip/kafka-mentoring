package org.example.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Signal {
    @JsonProperty("vehicleId")
    private Long vehicleId;
    @JsonProperty("details")
    private String details;
    @JsonProperty("coordinate")
    private Integer coordinate;
    @JsonProperty("destination")
    private Integer destination;
}
