package com.sdd.cinemaallocationsinfra.repository.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.sdd.cinemaallocations.TicketBooth;
import com.sdd.cinemaallocationsinfra.repository.JPAMovieScreeningRepository;
import com.sdd.cinemaallocationsinfra.repository.SpringMovieScreeningRepository;
import com.sdd.cinemaallocationsinfra.repository.helpers.stubmodel.MovieScreeningDto;
import com.sdd.cinemaallocationsinfra.repository.model.JPAMovieScreening;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Given {

    static public class The {
        public static final String FORD_THEATER = "1";
        public static final String O3_AUDITORIUM = "2";
        public static final String DOCK_STREET = "3";
        public static final String MADISON_THEATER = "5";

        public static String ford_theater_id;
        public static String os_auditorium_id;
        public static String dock_street_id;
        public static String madison_theater_id;

        public static TicketBooth fordTheaterTicketBooth(SpringMovieScreeningRepository springMovieScreeningRepository) throws IOException {
            JPAMovieScreening movieScreening = springMovieScreeningRepository.save(findJPAMovieScreeningFromId(FORD_THEATER));
            ford_theater_id = movieScreening.id();
            return new TicketBooth(new JPAMovieScreeningRepository(springMovieScreeningRepository));
        }

        public static TicketBooth o3AuditoriumTicketBooth(SpringMovieScreeningRepository springMovieScreeningRepository) throws IOException {
            JPAMovieScreening movieScreening = springMovieScreeningRepository.save(findJPAMovieScreeningFromId(O3_AUDITORIUM));
            os_auditorium_id = movieScreening.id();
            return new TicketBooth(new JPAMovieScreeningRepository(springMovieScreeningRepository));
        }

        public static TicketBooth dockStreetTicketBooth(SpringMovieScreeningRepository springMovieScreeningRepository) throws IOException {
            JPAMovieScreening movieScreening = springMovieScreeningRepository.save(findJPAMovieScreeningFromId(DOCK_STREET));
            dock_street_id = movieScreening.id();
            return new TicketBooth(new JPAMovieScreeningRepository(springMovieScreeningRepository));
        }


        public static TicketBooth madisonTheaterTicketBooth(SpringMovieScreeningRepository springMovieScreeningRepository) throws IOException {
            JPAMovieScreening movieScreening = springMovieScreeningRepository.save(findJPAMovieScreeningFromId(MADISON_THEATER));
            madison_theater_id = movieScreening.id();
            return new TicketBooth(new JPAMovieScreeningRepository(springMovieScreeningRepository));
        }

        private static JPAMovieScreening findJPAMovieScreeningFromId(String showId) throws IOException {
            Map<String, MovieScreeningDto> repository = new HashMap<>();

            String jsonDirectory = Paths.get(System.getProperty("user.dir")).getParent().getParent().getParent().toString() + "/Stubs/MovieScreenings";

            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(jsonDirectory));

            for (Path path : directoryStream) {
                String fileName = path.getFileName().toString();
                ObjectMapper mapper = new ObjectMapper().registerModule(new GuavaModule());
                repository.put(fileName.split("-")[0], mapper.readValue(path.toFile(), MovieScreeningDto.class));
            }
            if (repository.containsKey(showId)) {
                return repository.get(showId).toJpaModel(showId);
            } else {
                throw new IllegalArgumentException("MovieScreening not found for screening ID: " + showId);
            }
        }
    }
}
