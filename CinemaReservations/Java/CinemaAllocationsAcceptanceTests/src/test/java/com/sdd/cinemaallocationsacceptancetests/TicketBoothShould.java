package com.sdd.cinemaallocationsacceptancetests;

import com.sdd.cinemaallocations.*;
import com.sdd.cinemaallocationsacceptancetests.helpers.StubMovieScreeningRepository;
import org.junit.Test;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

public class TicketBoothShould {

    private static final String FORD_THEATER = "1";
    private static final String O3_AUDITORIUM = "2";
    private static final String DOCK_STREET = "3";
    private static final String MADISON_THEATER = "5";

    private MovieScreeningRepository repository = new StubMovieScreeningRepository();

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
    public void Reserve_multiple_seats_when_available() {
        int partyRequested = 3;

        TicketBooth ticketBooth = new TicketBooth(repository);
        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(DOCK_STREET, partyRequested));

        assertThat(seatsAllocated.reservedSeats()).hasSize(3);
        assertThat((seatsAllocated.seatNames())).containsExactly("A6", "A7", "A8");
    }

    @Test
    public void return_SeatsNotAvailable_when_all_seats_are_unavailable() {
        int partyRequested = 1;

        TicketBooth ticketBooth = new TicketBooth(repository);
        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(MADISON_THEATER, partyRequested));

        assertThat(seatsAllocated).isInstanceOf(NoPossibleAllocationsFound.class);
    }

    @Test
    public void return_TooManyTicketsRequested_when_9_tickets_are_requested() {
        int partyRequested = 9;

        TicketBooth ticketBooth = new TicketBooth(repository);
        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(MADISON_THEATER, partyRequested));

        assertThat(seatsAllocated).isInstanceOf(TooManyTicketsRequested.class);
    }

    @Test
    public void reserve_three_adjacent_seats_when_available() {
        int partyRequested = 3;

        TicketBooth ticketBooth = new TicketBooth(repository);
        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(O3_AUDITORIUM, partyRequested));

        assertThat(seatsAllocated.reservedSeats()).hasSize(3);
        assertThat((seatsAllocated.seatNames())).containsExactly("A8", "A9", "A10");
    }

    @Test
    public void return_NoPossibleAdjacentSeatsFound_when_4_tickets_are_requested() {
        int partyRequested = 4;

        TicketBooth ticketBooth = new TicketBooth(repository);
        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(O3_AUDITORIUM, partyRequested));

        throw new UnsupportedOperationException("NOT IMPLEMENTED");
    }

}
