package com.sdd.cinemaallocationsacceptancetests.helpers.stubmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.sdd.cinemaallocations.MovieScreening;
import com.sdd.cinemaallocations.Row;
import com.sdd.cinemaallocations.Seat;
import com.sdd.cinemaallocations.SeatAvailability;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class MovieScreeningDto {

    @JsonProperty("Rows")
    private ImmutableMap<String, ImmutableList<SeatDto>> rowsDto;

    public MovieScreening toDomain() {
        Map<String, Row> rows = new HashMap<>();

        for (Map.Entry<String, ImmutableList<SeatDto>> rowDto : rowsDto.entrySet()) {
            List<Seat> seats = new ArrayList<>();

            rowDto.getValue().forEach(seatDto -> {
                String rowName = rowDto.getKey();
                int number = extractNumber(seatDto.name());

                SeatAvailability seatAvailability = extractAvailability(seatDto.seatAvailability());


                seats.add(new Seat(rowName, number, seatAvailability));
            });

            rows.put(rowDto.getKey(), new Row(rowDto.getKey(), seats));
        }

        return new MovieScreening(rows);
    }

    private SeatAvailability extractAvailability(final String seatAvailability) {

        switch(seatAvailability) {
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
