package com.eacuamba.dev.ustmflix.graph;

import lombok.*;
import lombok.experimental.*;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class RatingNode extends Node<Double> {

    public RatingNode(String name, Double value) {
        super(name, value);
        //TODO Auto-generated constructor stub
    }
    
}
