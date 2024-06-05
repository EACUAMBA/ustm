package com.eacuamba.dev.esa_proxy_design_pattern.import_movies_csv;

import java.util.List;

public interface ICsvMoviesService {
    List<MovieDto> getAll();
}
