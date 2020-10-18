package com.sdd.cinemaallocationsinfra.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JPASeat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String rowName;
    private int number;

    @Enumerated(EnumType.STRING)
    private JPASeatAvailability seatAvailability;
}
