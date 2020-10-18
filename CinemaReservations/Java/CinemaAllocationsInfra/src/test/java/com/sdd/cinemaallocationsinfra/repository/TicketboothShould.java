package com.sdd.cinemaallocationsinfra.repository;

import com.sdd.cinemaallocations.*;
import com.sdd.cinemaallocationsinfra.helpers.Given;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TicketboothShould {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SpringMovieScreeningRepository springMovieScreeningRepository;

    @Test
    public void reserve_one_seat_when_available() throws IOException {

        TicketBooth ticketBooth = Given.The.fordTheaterTicketBooth(springMovieScreeningRepository);
        int partyRequested = 1;

        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(Given.The.ford_theater_id, partyRequested));

        assertThat(seatsAllocated.reservedSeats()).hasSize(1);
        assertThat(seatsAllocated.reservedSeats().get(0).toString()).isEqualTo("A3");

    }

    @Test
    public void Reserve_multiple_seats_when_available() throws IOException {
        TicketBooth ticketBooth = Given.The.dockStreetTicketBooth(springMovieScreeningRepository);

        int partyRequested = 3;

        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(Given.The.dock_street_id, partyRequested));

        assertThat(seatsAllocated.reservedSeats()).hasSize(3);
        assertThat((seatsAllocated.seatNames())).containsExactly("A6", "A7", "A8");
    }

    @Test
    public void return_SeatsNotAvailable_when_all_seats_are_unavailable() throws IOException {
        TicketBooth ticketBooth = Given.The.madisonTheaterTicketBooth(springMovieScreeningRepository);

        int partyRequested = 1;

        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(Given.The.madison_theater_id, partyRequested));

        assertThat(seatsAllocated).isInstanceOf(NoPossibleAllocationsFound.class);
    }

    @Test
    public void return_TooManyTicketsRequested_when_9_tickets_are_requested() throws IOException {
        TicketBooth ticketBooth = Given.The.madisonTheaterTicketBooth(springMovieScreeningRepository);

        int partyRequested = 9;

        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(Given.The.madison_theater_id, partyRequested));

        assertThat(seatsAllocated).isInstanceOf(TooManyTicketsRequested.class);
    }

    @Test
    public void reserve_three_adjacent_seats_when_available() throws IOException {
        TicketBooth ticketBooth = Given.The.o3AuditoriumTicketBooth(springMovieScreeningRepository);

        int partyRequested = 3;

        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(Given.The.os_auditorium_id, partyRequested));

        assertThat(seatsAllocated.reservedSeats()).hasSize(3);
        assertThat((seatsAllocated.seatNames())).containsExactly("A8", "A9", "A10");
    }

    @Test
    public void return_NoPossibleAdjacentSeatsFound_when_4_tickets_are_requested() throws IOException {
        TicketBooth ticketBooth = Given.The.o3AuditoriumTicketBooth(springMovieScreeningRepository);

        int partyRequested = 4;

        SeatsAllocated seatsAllocated = ticketBooth.allocateSeats(new AllocateSeats(Given.The.os_auditorium_id, partyRequested));

        assertThat(seatsAllocated).isInstanceOf(NoPossibleAdjacentSeatsFound.class);
    }
}
