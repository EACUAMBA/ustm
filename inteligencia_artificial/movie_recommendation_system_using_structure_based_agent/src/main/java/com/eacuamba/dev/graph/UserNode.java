package com.eacuamba.dev.graph;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class UserNode extends Node<String> {
    
}
