package com.eacuamba.dev.hello_world;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ImdbMovieDto {
    private Integer id;

    @CsvBindByName(column = "Poster_Link")
    private String posterLink;

    @CsvBindByName(column = "Series_Title")
    private String seriesTitle;

    @CsvBindByName(column = "Released_Year")
    private String releasedYear;

    @CsvBindByName(column = "Certificate")
    private String certificate;

    @CsvBindByName(column = "Runtime")
    private String runtime;

    @CsvBindByName(column = "Genre")
    private String genre;

    @CsvBindByName(column = "IMDB_Rating")
    private String imdbRating;

    @CsvBindByName(column = "Overview")
    private String overview;

    @CsvBindByName(column = "Meta_score")
    private String metaScore;

    @CsvBindByName(column = "Director")
    private String director;

    @CsvBindByName(column = "Star1")
    private String star1;

    @CsvBindByName(column = "Star2")
    private String star2;

    @CsvBindByName(column = "Star3")
    private String star3;

    @CsvBindByName(column = "Star4")
    private String star4;

    @CsvBindByName(column = "No_of_Votes")
    private String noVfVotes;

    @CsvBindByName(column = "Gross")
    private String gross;
}
