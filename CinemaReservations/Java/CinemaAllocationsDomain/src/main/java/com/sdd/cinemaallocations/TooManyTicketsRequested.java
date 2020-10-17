package com.sdd.cinemaallocations;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.List;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TooManyTicketsRequested extends SeatsAllocated {

    public TooManyTicketsRequested(int partyRequested, List<Seat> reservedSeats) {
        super(partyRequested, reservedSeats);
    }
}