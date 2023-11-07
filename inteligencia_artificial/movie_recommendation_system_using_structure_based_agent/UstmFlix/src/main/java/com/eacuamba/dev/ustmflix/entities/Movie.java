package com.eacuamba.dev.ustmflix.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    private Integer index;
    private String directorName;
    private Double duration;
    private String actorOneName;
    private String actorTwoName;
    private String actorThreeName;
    private String genres;
    private String title;
    private String language;
    private String country;
    private Integer year;
    private Double imdbScore;

    public String genreFormatted(){
        return this.getGenreList().stream().reduce((a, b) -> a +", " + b ).get();
    }

    public List<String> getGenreList(){
        return Optional.ofNullable(this.genres).map(s -> Arrays.stream(s.split("[|]")).toList()).orElse(new ArrayList<>());
    }
}
