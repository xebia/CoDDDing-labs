package com.sdd.cinemaallocationsacceptancetests.helpers.stubmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


public enum SeatAvailability {
    Reserved,
    Available,
    Confirmed


}
