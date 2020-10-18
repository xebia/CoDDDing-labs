package com.sdd.cinemaallocationsinfra.repository;

import com.sdd.cinemaallocations.MovieScreening;
import com.sdd.cinemaallocations.MovieScreeningRepository;
import com.sdd.cinemaallocationsinfra.repository.model.JPAMovieScreening;

public class JPAMovieScreeningRepository implements MovieScreeningRepository {

    private final SpringMovieScreeningRepository springMovieScreeningRepository;

    public JPAMovieScreeningRepository(SpringMovieScreeningRepository springMovieScreeningRepository) {
        this.springMovieScreeningRepository = springMovieScreeningRepository;
    }

    @Override
    public MovieScreening findMovieScreeningById(String screeningId) {
        return springMovieScreeningRepository.findById(screeningId).map(JPAMovieScreening::toDomain).orElse(null);
    }

}
