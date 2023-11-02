package com.eacuamba.dev.graph;

import com.eacuamba.dev.entities.Movie;

import lombok.*;
import lombok.experimental.*;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class MovieNode extends Node<Movie> {

    public MovieNode(String name, Movie value) {
        super(name, value);
        //TODO Auto-generated constructor stub
    }
}
