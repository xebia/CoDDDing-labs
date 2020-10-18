package com.sdd.cinemaallocationsinfra.repository.model;

import com.sdd.cinemaallocations.Row;
import com.sdd.cinemaallocations.Seat;
import com.sdd.cinemaallocations.SeatAvailability;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JPARow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<JPASeat> jpaSeats = new ArrayList<>();

    public Row toDomain() {
        List<Seat> seats = new ArrayList<>();
        for (JPASeat jpaSeat : jpaSeats) {
            SeatAvailability seatAvailability = extractAvailability(jpaSeat.seatAvailability().name());
            seats.add(new Seat(name, jpaSeat.number(), seatAvailability));
        }
        return new Row(name, seats);

    }

    private SeatAvailability extractAvailability(final String seatAvailability) {

        switch (seatAvailability) {
            case "Available":
                return SeatAvailability.Available;
            case "Reserved":
                return SeatAvailability.Reserved;
            case "Confirmed":
                return SeatAvailability.Confirmed;
            default:
                throw new IllegalStateException("Unexpected value: " + seatAvailability);
        }
    }

    private static int extractNumber(final String name) {
        return Integer.parseUnsignedInt(name.substring(1));
    }

}
