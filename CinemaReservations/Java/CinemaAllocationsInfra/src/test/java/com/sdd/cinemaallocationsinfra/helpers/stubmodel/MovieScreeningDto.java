package com.sdd.cinemaallocationsinfra.helpers.stubmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.sdd.cinemaallocationsinfra.repository.model.JPAMovieScreening;
import com.sdd.cinemaallocationsinfra.repository.model.JPARow;
import com.sdd.cinemaallocationsinfra.repository.model.JPASeat;
import com.sdd.cinemaallocationsinfra.repository.model.JPASeatAvailability;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class MovieScreeningDto {

    @JsonProperty("Rows")
    private ImmutableMap<String, ImmutableList<SeatDto>> rowsDto;

    public JPAMovieScreening toJpaModel(String id) {
        Map<String, JPARow> rows = new HashMap<>();


        for (Map.Entry<String, ImmutableList<SeatDto>> rowDto : rowsDto.entrySet()) {
            List<JPASeat> seats = new ArrayList<>();

            rowDto.getValue().forEach(seatDto -> {
                JPASeat jpaSeat = new JPASeat();
                jpaSeat.id(new Random().nextLong());
                jpaSeat.rowName(rowDto.getKey());
                jpaSeat.number(extractNumber(seatDto.name()));
                jpaSeat.seatAvailability(extractAvailability(seatDto.seatAvailability()));
                seats.add(jpaSeat);
            });
            JPARow jpaRow = new JPARow();
            jpaRow.id(new Random().nextLong());
            jpaRow.name(rowDto.getKey());
            jpaRow.jpaSeats(seats);

            rows.put(rowDto.getKey(), jpaRow);
        }

        JPAMovieScreening jpaMovieScreening = new JPAMovieScreening();
        jpaMovieScreening.id(id);
        jpaMovieScreening.jpaRows(rows);
        return jpaMovieScreening;
    }

    private JPASeatAvailability extractAvailability(final String seatAvailability) {

        switch(seatAvailability) {
            case "Available":
                return JPASeatAvailability.Available;
            case "Reserved":
                return JPASeatAvailability.Reserved;
            case "Confirmed":
                return JPASeatAvailability.Confirmed;
            default:
                throw new IllegalStateException("Unexpected value: " + seatAvailability);
        }
    }

    private static int extractNumber(final String name) {
        return Integer.parseUnsignedInt(name.substring(1));
    }
}
