package com.eacuamba.dev.esa_proxy_design_pattern.import_movies_csv;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class ImportMoviesCsvService implements ICsvMoviesService {
    private static ImportMoviesCsvService importMoviesCsvService;

    public static ImportMoviesCsvService getInstance() {
        if (importMoviesCsvService == null) {
            importMoviesCsvService = new ImportMoviesCsvService();
        }
        return importMoviesCsvService;
    }

    private ImportMoviesCsvService() {
    }

    public List<MovieDto> getAll() {
        List<MovieDto> movies = new ArrayList<>();

        try (
                InputStream resourceAsStream = this.getClass().getResourceAsStream("/imdb_top_400.csv");
                InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(resourceAsStream));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            List<String> allLines = bufferedReader.lines().collect(Collectors.toList());
            allLines.remove(0);

            for (String line : allLines) {
                String[] movieLine = line.split(",");

                MovieDto movieDto = new MovieDto();
                movieDto.setGenre(movieLine[0]);
                movieDto.setTitle(movieLine[1]);
                movieDto.setYear(movieLine[2]);
                movieDto.setRating(movieLine[3]);

                movies.add(movieDto);
            }
            TimeUnit.SECONDS.sleep(5);//simulate request load time
            log.info(" {} Movies loaded successfully from the CSV file.", movies.size());
        } catch (Exception e) {
            log.error("Failed to load all movies", e);
        }

        return movies;
    }
}
