package com.eacuamba.dev.hello_world;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class ImdbMovieController {
    private final ImdbMovieService imdbMovieService;

    @GetMapping()
    public List<ImdbMovieDto> get(
            @RequestParam(name = "limit", required = false) Integer limit,
            @RequestParam(name = "offset", required = false) Integer offset
    ) {
        return this.imdbMovieService.get(limit, offset);
    }
}
