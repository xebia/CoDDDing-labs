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

    public Seat reserveSeats() {
        return new Seat(this.rowName, this.number, SeatAvailability.Reserved);
    }

    public boolean isAvailable() {
        return seatAvailability == SeatAvailability.Available;
    }

    public boolean sameSeatLocation(Seat seat) {
        return rowName.equals(seat.rowName) && number == seat.number;
    }

    @Override
    public String toString() {
        return rowName + number;
    }
}
