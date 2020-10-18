package com.sdd.cinemaallocationsinfra.controllers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdd.cinemaallocations.Seat;
import lombok.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class SeatsAllocated {

    @JsonProperty("PartyRequested")
    private int partyRequested;

    @JsonProperty("ReservedSeats")
    private List<Seat> reservedSeats;

    public List<String> seatNames() {
        return reservedSeats.stream().sorted(Comparator.comparing(Seat::number)).map(Seat::toString).collect(Collectors.toList());
    }
}
