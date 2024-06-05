package com.eacuamba.dev.esa_proxy_design_pattern.import_movies_csv;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {
    private String title;
    private String year;
    private String genre;
    private String rating;
}
