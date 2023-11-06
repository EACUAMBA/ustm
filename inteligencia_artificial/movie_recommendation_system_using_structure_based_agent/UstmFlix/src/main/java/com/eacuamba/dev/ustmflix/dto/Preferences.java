package com.eacuamba.dev.ustmflix.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
public class Preferences {
    private final List<String> genres = new ArrayList<>();
    private final List<String> directors = new ArrayList<>();
    private final List<String> actors = new ArrayList<>();
    private final List<Double> ranking = new ArrayList<>();
}
