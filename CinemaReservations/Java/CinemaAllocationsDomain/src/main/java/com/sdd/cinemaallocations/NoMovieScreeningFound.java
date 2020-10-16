package com.sdd.cinemaallocations;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.List;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NoMovieScreeningFound extends SeatsAllocated {

    public NoMovieScreeningFound(int partyRequested, List<Seat> reservedSeats) {
        super(partyRequested, reservedSeats);
    }
}
