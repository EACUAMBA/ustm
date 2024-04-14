package com.eacuamba.dev.hello_world;

import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.requireNonNullElse;

@Service
public class ImdbMovieService {
    private final List<ImdbMovieDto> imdbMovieDtoList = new ArrayList<>();

    public List<ImdbMovieDto> get(Integer limit, Integer offset) {


        return this.imdbMovieDtoList.stream()
                .skip(requireNonNullElse(offset, 0))
                .limit(requireNonNullElse(limit, this.imdbMovieDtoList.size()))
                .collect(Collectors.toList());
    }

    private ImdbMovieDto setId(Integer id, ImdbMovieDto imdbMovieDto) {
        imdbMovieDto.setId(id);
        return imdbMovieDto;
    }

    @PostConstruct
    public void PostConstruct() {
        this.imdbMovieDtoList.addAll(readCsv());
    }

    private List<ImdbMovieDto> readCsv() {
        try {
            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX.concat("imdb_top_1000.csv"));

            List<ImdbMovieDto> imdbMovieDtoList = new CsvToBeanBuilder<>(Files.newBufferedReader(file.toPath()))
                    .withType(ImdbMovieDto.class)
                    .build()
                    .parse()
                    .stream()
                    .map(ImdbMovieDto.class::cast)
                    .toList();

            return IntStream.range(0, imdbMovieDtoList.size())
                    .mapToObj(id -> setId(id, imdbMovieDtoList.get(id)))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
