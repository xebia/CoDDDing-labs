package com.sdd.cinemaallocations;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Seat {

    private String rowName;
    private int number;
    private SeatAvailability seatAvailability;

    public Seat(String rowName, int number, SeatAvailability seatAvailability) {
        this.rowName = rowName;
        this.number = number;
        this.seatAvailability = seatAvailability;
    }

    @Override
    public String toString() {
        return rowName + number;
    }
}
