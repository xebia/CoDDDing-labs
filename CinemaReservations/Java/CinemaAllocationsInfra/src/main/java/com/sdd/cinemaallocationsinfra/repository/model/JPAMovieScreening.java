package com.sdd.cinemaallocationsinfra.repository.model;

import com.sdd.cinemaallocations.MovieScreening;
import com.sdd.cinemaallocations.Row;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JPAMovieScreening {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @MapKey(name = "name")
    private Map<String, JPARow> jpaRows = new HashMap<>();

    public MovieScreening toDomain() {
        Map<String, Row> rows = new HashMap<>();

        for (Map.Entry<String, JPARow> jpaRow : jpaRows.entrySet()) {

            rows.put(jpaRow.getKey(), jpaRow.getValue().toDomain());

        }

        return new MovieScreening(rows);
    }


}
