package com.sdd.cinemaallocationsinfra.repository.helpers.stubmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.sdd.cinemaallocationsinfra.repository.model.JPAMovieScreening;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class MovieScreeningDto {

    @JsonProperty("Rows")
    private ImmutableMap<String, ImmutableList<SeatDto>> rowsDto;

    public JPAMovieScreening toJpaModel(String id) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED FUNCTION IN JPAMovieScreeningRepository");

    }
}
