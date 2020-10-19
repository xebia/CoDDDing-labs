package com.sdd.cinemaallocationsinfra.controllers;

import com.sdd.cinemaallocations.*;
import com.sdd.cinemaallocationsinfra.controllers.model.SeatsAllocated;
import com.sdd.cinemaallocationsinfra.repository.JPAMovieScreeningRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/moviescreening")
public class MovieScreeningController {

    private TicketBooth ticketBooth;

    public MovieScreeningController(JPAMovieScreeningRepository jpaMovieScreeningRepository) {
        ticketBooth = new TicketBooth(jpaMovieScreeningRepository);
    }

    @PostMapping(value = "/{showId}/allocateseats/{partyRequested}", produces = "application/json")
    public ResponseEntity<SeatsAllocated> allocateSeats(@PathVariable String showId, @PathVariable Integer partyRequested) {
        AllocateSeats allocateSeats = new AllocateSeats(showId, partyRequested);
        com.sdd.cinemaallocations.SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(allocateSeats);
        if (seatsAllocated instanceof TooManyTicketsRequested) {
            return ResponseEntity.badRequest().build();
        } else if (seatsAllocated instanceof NoPossibleAdjacentSeatsFound) {
            return ResponseEntity.notFound().build();
        } else if (seatsAllocated instanceof NoPossibleAllocationsFound) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(SeatsAllocated.fromDomain(seatsAllocated));
        }
    }

}
