package org.epam.learn.model;


import lombok.Data;

@Data
public class SignalRequest {
    private Long vehicleId;
    private String details;
    private Integer coordinate;
}
