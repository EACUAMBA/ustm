package com.eacuamba.dev.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Builder.Default
    private List<String> genreList = new ArrayList<>();
    private String title;
    private String language;
    private String country;
    private Integer year;
    private Double imdbScore;
}
