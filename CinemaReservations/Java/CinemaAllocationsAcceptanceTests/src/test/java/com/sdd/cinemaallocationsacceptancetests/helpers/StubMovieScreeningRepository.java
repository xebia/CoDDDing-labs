package com.sdd.cinemaallocationsacceptancetests.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableList;
import com.sdd.cinemaallocations.*;
import com.sdd.cinemaallocationsacceptancetests.helpers.stubmodel.MovieScreeningDto;
import com.sdd.cinemaallocationsacceptancetests.helpers.stubmodel.SeatDto;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StubMovieScreeningRepository implements MovieScreeningRepository {

    private Map<String, MovieScreeningDto> repository = new HashMap<>();

    public StubMovieScreeningRepository() throws IOException {
        String jsonDirectory = Paths.get(System.getProperty("user.dir")).getParent().getParent().getParent().toString() + "/Stubs/MovieScreenings";

        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(jsonDirectory));

        for (Path path : directoryStream) {
            String fileName = path.getFileName().toString();
            ObjectMapper mapper = new ObjectMapper().registerModule(new GuavaModule());
            repository.put(fileName.split("-")[0], mapper.readValue(path.toFile(), MovieScreeningDto.class));
        }
    }

    @Override
    public MovieScreening findMovieScreeningById(final String showId)  {
        if (repository.containsKey(showId)) {
            return repository.get(showId).toDomain();
        } else {
            throw new IllegalArgumentException("MovieScreening not found for screening ID: " + showId);
        }
    }


}
