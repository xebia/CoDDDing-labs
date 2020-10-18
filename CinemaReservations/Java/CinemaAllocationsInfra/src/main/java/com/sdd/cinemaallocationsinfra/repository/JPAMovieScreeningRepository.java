package com.sdd.cinemaallocationsinfra.repository;

import com.sdd.cinemaallocations.MovieScreening;
import com.sdd.cinemaallocations.MovieScreeningRepository;

public class JPAMovieScreeningRepository implements MovieScreeningRepository {

    private final SpringMovieScreeningRepository springMovieScreeningRepository;

    public JPAMovieScreeningRepository(SpringMovieScreeningRepository springMovieScreeningRepository) {
        this.springMovieScreeningRepository = springMovieScreeningRepository;
    }

    @Override
    public MovieScreening findMovieScreeningById(String screeningId) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED FUNCTION IN JPAMovieScreeningRepository");
    }

}
