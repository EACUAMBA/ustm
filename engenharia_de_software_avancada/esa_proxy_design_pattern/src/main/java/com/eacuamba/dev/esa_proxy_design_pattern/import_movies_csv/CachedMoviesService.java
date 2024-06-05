package com.eacuamba.dev.esa_proxy_design_pattern.import_movies_csv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
@RequiredArgsConstructor
public class CachedMoviesService implements ICsvMoviesService {
    private final ImportMoviesCsvService importMoviesCsvService;

    private LocalDateTime lastLoadWasAt;
    private final List<MovieDto> movieDtoList = new ArrayList<>();

    @Override
    public List<MovieDto> getAll() {
        log.info("Getting all movies!");

        if (nonNull(lastLoadWasAt)) {
            log.info("Last load was at {}", lastLoadWasAt);

            log.info("Checking if we need to load again!");

            long seconds = Duration.between(lastLoadWasAt, LocalDateTime.now()).toSeconds();

            log.info("Passed {} seconds since last load.", seconds);

            if(seconds < 30) {
                log.info("We don't need to load again, returning cached before.");
                return movieDtoList;
            }
        }

        log.info("Loading movies from CSV file.");
        this.lastLoadWasAt = LocalDateTime.now();
        this.movieDtoList.addAll(this.importMoviesCsvService.getAll());
        return this.movieDtoList;
    }
}
