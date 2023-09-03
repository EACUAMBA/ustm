package load_from_json;

import graph.Graph;
import graph.Node;

public class GraphDtoToGraph {

    public static Graph mapTpGraph(GraphDto graphDto) {

        Graph graph = new Graph();
        graphDto
                .getEdges()
                .forEach(edgeDto ->
                        graph.addEdge(
                                edgeDto.weight,
                                edgeDto.getName(),
                                new Node(graph, edgeDto.getLeftNode().getName()),
                                new Node(graph, edgeDto.getRightNode().getName())
                        )
                );

        return graph;
    }

    public static void mapTpGraph(GraphDto graphDto, Graph graph) {
        graphDto
                .getEdges()
                .forEach(edgeDto ->
                        graph.addEdge(
                                edgeDto.weight,
                                edgeDto.getName(),
                                new Node(graph, edgeDto.getLeftNode().getName()),
                                new Node(graph, edgeDto.getRightNode().getName())
                        )
                );
    }
}
