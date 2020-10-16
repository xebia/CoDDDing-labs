package com.sdd.cinemaallocationsacceptancetests;

import com.sdd.cinemaallocations.AllocateSeats;
import com.sdd.cinemaallocations.MovieScreeningRepository;
import com.sdd.cinemaallocations.SeatsAllocated;
import com.sdd.cinemaallocations.TicketBooth;
import com.sdd.cinemaallocationsacceptancetests.helpers.StubMovieScreeningRepository;
import org.junit.Test;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

public class TicketBoothShould {

    private static final String FORD_THEATER = "1";
    private static final String DOCK_STREET = "3";
    private static final String MADISON_THEATER = "5";

    private final MovieScreeningRepository repository = new StubMovieScreeningRepository();

    public TicketBoothShould() throws IOException {
    }

    @Test
    public void reserve_one_seat_when_available() {
        int partyRequested = 1;

        TicketBooth ticketBooth = new TicketBooth(repository);
        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(FORD_THEATER, partyRequested));

        assertThat(seatsAllocated.reservedSeats()).hasSize(1);
        assertThat(seatsAllocated.reservedSeats().get(0).toString()).isEqualTo("A3");
    }

    @Test
    public void return_SeatsNotAvailable_when_all_seats_are_unavailable() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Test
    public void return_TooManyTicketsRequested_when_9_tickets_are_requested() throws IOException {
        throw new UnsupportedOperationException();
    }

}
