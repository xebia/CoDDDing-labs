package com.sdd.cinemaallocationsinfra.controllers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    public static SeatsAllocated fromDomain(com.sdd.cinemaallocations.SeatsAllocated domainSeatsAllocated) {
        List<Seat> seats = new ArrayList<>();
        domainSeatsAllocated.reservedSeats().forEach(domainSeat -> seats.add(new Seat(domainSeat.rowName(), domainSeat.number(), domainSeat.seatAvailability().name())));
        return new SeatsAllocated(domainSeatsAllocated.partyRequested(), seats);
    }
}
