package com.eacuamba.dev.ustmflix.graph;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class GenreNode extends Node<String> {

    public GenreNode(String name, String value) {
        super(name, value);
        //TODO Auto-generated constructor stub
    }
    
}
