package com.eacuamba.dev.ustmflix.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @Builder.Default
    private List<String> genreList = new ArrayList<>();
    private String title;
    private String language;
    private String country;
    private Integer year;
    private Double imdbScore;

    public String genreFormatted(){
        return this.genreList.stream().reduce((a, b) -> a +", " + b ).get();
    }
}
