package com.sdd.cinemaallocationsinfra.repository.model;

import com.sdd.cinemaallocations.MovieScreening;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JPAMovieScreening {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;


    public MovieScreening toDomain() {
        throw new UnsupportedOperationException("NOT IMPLEMENTED FUNCTION IN JPAMovieScreeningRepository");
    }


}
