package com.sdd.cinemaallocations;


import java.util.ArrayList;

public class TicketBooth {
    private MovieScreeningRepository movieScreeningRepository;
    private final int MAXIMUM_NUMBER_OF_ALLOWED_TICKETS = 8;

    public TicketBooth(MovieScreeningRepository repo)
    {
        this.movieScreeningRepository = repo;
    }

    public SeatsAllocated allocateSeats(AllocateSeats allocateSeats)  {
        if (allocateSeats.partyRequested() > MAXIMUM_NUMBER_OF_ALLOWED_TICKETS) {
            return new TooManyTicketsRequested(allocateSeats.partyRequested(), new ArrayList<>());
        }
        MovieScreening movieScreening = movieScreeningRepository.findMovieScreeningById(allocateSeats.showId());
        return movieScreening.allocateSeats(allocateSeats);
    }
}
