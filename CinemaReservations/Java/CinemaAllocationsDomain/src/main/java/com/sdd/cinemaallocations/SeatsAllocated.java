package com.sdd.cinemaallocations;

import lombok.Value;
import lombok.experimental.NonFinal;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Value
@NonFinal
public class SeatsAllocated {
    int partyRequested;
    List<Seat> reservedSeats;

    public List<String> seatNames() {
        return reservedSeats.stream().sorted(Comparator.comparing(Seat::number)).map(Seat::toString).collect(Collectors.toList());
    }
}
