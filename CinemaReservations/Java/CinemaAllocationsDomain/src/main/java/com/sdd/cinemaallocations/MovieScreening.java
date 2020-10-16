package com.sdd.cinemaallocations;

import lombok.Getter;

import java.util.Map;

@Getter
public class MovieScreening {
    private Map<String, Row> rows;

    public MovieScreening(Map<String, Row> rows) {
        this.rows = rows;
    }


}
