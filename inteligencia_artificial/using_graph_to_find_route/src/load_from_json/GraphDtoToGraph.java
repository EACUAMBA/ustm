package load_from_json;

import graph.Graph;
import graph.Node;

public class GraphDtoToGraph {

    public static GraphDto mapTpGraphDto(Graph graph) {

        GraphDto graphDto = new GraphDto();

        graph
                .getEdgeList()
                .forEach(edge -> {
                    EdgeDto edgeDto = new EdgeDto();
                    edgeDto.setUuidd(edge.getUuid());
                    edgeDto.setName(edge.getName());
                    edgeDto.setWeight(edge.getWeight());
                    edgeDto.setLeftNode(new NodeDto(edge.getLeftNode().getUuid(), edge.getLeftNode().getName()));
                    edgeDto.setRightNode(new NodeDto(edge.getRightNode().getUuid(), edge.getRightNode().getName()));
                    graphDto.getEdges().add(edgeDto);
                });

        return graphDto;
    }

    public static void mapTpGraph(GraphDto graphDto, Graph graph) {
        graphDto
                .getEdges()
                .forEach(edgeDto ->
                        graph.addEdge(
                                edgeDto.getUuid(),
                                edgeDto.weight,
                                edgeDto.getName(),
                                Node.build(graph, edgeDto.leftNode),
                                Node.build(graph, edgeDto.rightNode)
                        )
                );
    }
}
