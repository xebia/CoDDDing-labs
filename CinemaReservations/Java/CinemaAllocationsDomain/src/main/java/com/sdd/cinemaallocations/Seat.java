package com.sdd.cinemaallocations;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public boolean isAdjacentWith(List<Seat> seats) {
        List<Seat> orderedSeats = seats.stream().sorted(Comparator.comparing(Seat::number)).collect(Collectors.toCollection(ArrayList::new));

        for(Seat seat : orderedSeats) {
            if (number +  1 == seat.number || number -  1 == seat.number) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return rowName + number;
    }
}
