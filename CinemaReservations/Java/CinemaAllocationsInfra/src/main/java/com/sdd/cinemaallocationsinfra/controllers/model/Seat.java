package com.sdd.cinemaallocationsinfra.controllers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Seat {

    @JsonProperty("RowName")
    private String rowName;

    @JsonProperty("Number")
    private int number;

    @JsonProperty("SeatAvailability")
    private String seatAvailability;

    @Override
    public String toString() {
        return rowName + number;
    }
}
