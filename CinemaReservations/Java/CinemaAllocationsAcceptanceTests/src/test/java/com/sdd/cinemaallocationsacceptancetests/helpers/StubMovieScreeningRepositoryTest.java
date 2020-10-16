package com.sdd.cinemaallocationsacceptancetests.helpers;

import com.sdd.cinemaallocations.MovieScreening;
import org.junit.Test;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;


public class StubMovieScreeningRepositoryTest {

    @Test
    public void find_movie_screening_one() throws IOException {
        StubMovieScreeningRepository repository = new StubMovieScreeningRepository();

        MovieScreening movieScreening = repository.findMovieScreeningById("1");

        assertThat(movieScreening).isNotNull();
        assertThat(movieScreening.rows()).hasSize(2);

    }

}