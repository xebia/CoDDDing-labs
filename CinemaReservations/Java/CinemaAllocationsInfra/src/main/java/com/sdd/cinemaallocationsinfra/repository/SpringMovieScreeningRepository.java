package com.sdd.cinemaallocationsinfra.repository;

import com.sdd.cinemaallocationsinfra.repository.model.JPAMovieScreening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringMovieScreeningRepository extends JpaRepository<JPAMovieScreening, String> {
}
