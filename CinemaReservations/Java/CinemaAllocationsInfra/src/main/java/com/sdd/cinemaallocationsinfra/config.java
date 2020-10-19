package com.sdd.cinemaallocationsinfra;

import com.sdd.cinemaallocationsinfra.repository.JPAMovieScreeningRepository;
import com.sdd.cinemaallocationsinfra.repository.SpringMovieScreeningRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.sdd.cinemaallocations")
public class config {

    @Bean
    public JPAMovieScreeningRepository jpaMovieScreeningRepository(SpringMovieScreeningRepository springMovieScreeningRepository) {
        return new JPAMovieScreeningRepository(springMovieScreeningRepository);
    }
}