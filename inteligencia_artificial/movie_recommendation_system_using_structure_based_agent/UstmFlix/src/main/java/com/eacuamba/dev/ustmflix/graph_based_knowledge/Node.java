package com.eacuamba.dev.ustmflix.graph_based_knowledge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Node <T> {
    private final Set<Edge> edgeList = new HashSet<>();
    private final Set<Node<?>> nodeList = new HashSet<>();
    private final String name;
    private final T value;

    public void addNode(Node<?> node) {
        this.nodeList.add(node);
    }

    public List<GenreNode> getGenreNodeList(){
        return this.nodeList.stream().filter(GenreNode.class::isInstance).map(GenreNode.class::cast).toList();
    }

    public List<DirectorNode> getDirectorNodeList(){
        return this.nodeList.stream().filter(DirectorNode.class::isInstance).map(DirectorNode.class::cast).toList();
    }

    public List<MovieNode> getMovieNodeList(){
        return this.nodeList.stream().filter(MovieNode.class::isInstance).map(MovieNode.class::cast).toList();
    }

    public List<ActorNode> getActorNodeList(){
        return this.nodeList.stream().filter(ActorNode.class::isInstance).map(ActorNode.class::cast).toList();
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    
}
