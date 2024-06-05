package com.eacuamba.dev.esa_proxy_design_pattern;

import com.eacuamba.dev.esa_proxy_design_pattern.import_movies_csv.CachedMoviesService;
import com.eacuamba.dev.esa_proxy_design_pattern.import_movies_csv.ImportMoviesCsvService;
import com.eacuamba.dev.esa_proxy_design_pattern.import_movies_csv.MovieDto;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class EsaProxyDesignPatternApplication {
    public static void main(String[] args) {

        ImportMoviesCsvService importMoviesCsvService = ImportMoviesCsvService.getInstance();
        CachedMoviesService cachedMoviesService = new CachedMoviesService(importMoviesCsvService);

        var app = Javalin.create(/*config*/)
                .get("/cached", ctx -> {
                    log.info("Using cached endpoint!");
                    List<MovieDto> movieDtoList = cachedMoviesService.getAll();
                    ctx.json(movieDtoList);
                })
                .get("/non-cached", ctx -> {
                    log.info("Using non-cached endpoint!");
                    List<MovieDto> movieDtoList = importMoviesCsvService.getAll();
                    ctx.json(movieDtoList);
                })
                .start(7070);
    }
}
