package com.sdd.cinemaallocationsinfra.system;

import com.sdd.cinemaallocationsinfra.CinemaAllocationsInfraApplication;
import com.sdd.cinemaallocationsinfra.controllers.model.SeatsAllocated;
import com.sdd.cinemaallocationsinfra.helpers.Given;
import com.sdd.cinemaallocationsinfra.repository.SpringMovieScreeningRepository;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = CinemaAllocationsInfraApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureTestDatabase
public class MovieScreeningControllerShould {

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private SpringMovieScreeningRepository springMovieScreeningRepository;

    @Before
    public void before() {
        RestAssured.baseURI = "http://localhost:" + randomServerPort + "/api/moviescreening/";
    }

    @Test
    public void reserve_one_seat_when_available() throws IOException {
        Given.The.fordTheaterTicketBooth(springMovieScreeningRepository);

        SeatsAllocated seatsAllocated =
                given()
                        .pathParam("showId", Given.The.ford_theater_id).pathParam("partyRequested", 1)
                        .when().post("/{showId}/allocateseats/{partyRequested}")
                        .then().statusCode(200).extract().as(SeatsAllocated.class);

        assertThat(seatsAllocated.reservedSeats()).hasSize(1);
        assertThat(seatsAllocated.reservedSeats().get(0).toString()).isEqualTo("A3");

    }

    @Test
    public void Reserve_multiple_seats_when_available() throws IOException {
        Given.The.dockStreetTicketBooth(springMovieScreeningRepository);

        SeatsAllocated seatsAllocated =
                given()
                        .pathParam("showId", Given.The.dock_street_id).pathParam("partyRequested", 3)
                        .when().post("/{showId}/allocateseats/{partyRequested}")
                        .then().statusCode(200).extract().as(SeatsAllocated.class);

        assertThat(seatsAllocated.reservedSeats()).hasSize(3);
        assertThat((seatsAllocated.seatNames())).containsExactly("A6", "A7", "A8");
    }

    @Test
    public void return_SeatsNotAvailable_when_all_seats_are_unavailable() throws IOException {
        Given.The.madisonTheaterTicketBooth(springMovieScreeningRepository);

        given()
                .pathParam("showId", Given.The.madison_theater_id).pathParam("partyRequested", 1)
                .when().post("/{showId}/allocateseats/{partyRequested}")
                .then().statusCode(404);

    }

    @Test
    public void return_TooManyTicketsRequested_when_9_tickets_are_requested() throws IOException {
        Given.The.madisonTheaterTicketBooth(springMovieScreeningRepository);

        given()
                .pathParam("showId", Given.The.madison_theater_id).pathParam("partyRequested", 9)
                .when().post("/{showId}/allocateseats/{partyRequested}")
                .then().statusCode(400);
    }

    @Test
    public void reserve_three_adjacent_seats_when_available() throws IOException {
        Given.The.o3AuditoriumTicketBooth(springMovieScreeningRepository);

        SeatsAllocated seatsAllocated =
                given()
                        .pathParam("showId", Given.The.os_auditorium_id).pathParam("partyRequested", 3)
                        .when().post("/{showId}/allocateseats/{partyRequested}")
                        .then().statusCode(200).extract().as(SeatsAllocated.class);

        assertThat(seatsAllocated.reservedSeats()).hasSize(3);
        assertThat((seatsAllocated.seatNames())).containsExactly("A8", "A9", "A10");
    }

    @Test
    public void return_NoPossibleAdjacentSeatsFound_when_4_tickets_are_requested() throws IOException {
        Given.The.o3AuditoriumTicketBooth(springMovieScreeningRepository);

        given()
                .pathParam("showId", Given.The.os_auditorium_id).pathParam("partyRequested", 4)
                .when().post("/{showId}/allocateseats/{partyRequested}")
                .then().statusCode(404);
    }
}
