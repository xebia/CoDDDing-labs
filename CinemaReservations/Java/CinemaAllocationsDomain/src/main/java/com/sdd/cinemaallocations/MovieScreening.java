package com.sdd.cinemaallocations;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Map;

@Getter
public class MovieScreening {
    private Map<String, Row> rows;

    public MovieScreening(Map<String, Row> rows) {
        this.rows = rows;
    }

    public SeatsAllocated allocateSeats(AllocateSeats allocateSeats) {
        int numberOfSeatsAvailable = 0;
        for( Row row : rows.values()) {
            SeatsAllocated seatsAllocated = row.allocateSeats(allocateSeats);
            if (!(seatsAllocated instanceof NoPossibleAllocationsFound)) {
                Row updatedRow = row.makeSeatsReserved(seatsAllocated.reservedSeats());
                rows.put(updatedRow.name(), updatedRow);
                return seatsAllocated;
            }
            numberOfSeatsAvailable = numberOfSeatsAvailable + row.returnNumberOfSeatsAvailable();
        }
        if(numberOfSeatsAvailable >= allocateSeats.partyRequested()) {
            return new NoPossibleAdjacentSeatsFound(allocateSeats.partyRequested(), new ArrayList<>());
        }
        return new NoPossibleAllocationsFound(allocateSeats.partyRequested(), new ArrayList<>());
    }


}
