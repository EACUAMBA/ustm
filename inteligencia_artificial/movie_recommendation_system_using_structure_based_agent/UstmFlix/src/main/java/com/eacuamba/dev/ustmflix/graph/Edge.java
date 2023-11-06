package com.eacuamba.dev.ustmflix.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Edge {
    private final List<Edge> edgeList = new ArrayList();
    private final List<Node> nodeList = new ArrayList();
    private final String name;
}
