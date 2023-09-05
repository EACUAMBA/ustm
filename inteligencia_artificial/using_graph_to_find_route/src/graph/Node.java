package graph;

import load_from_json.EdgeDto;
import load_from_json.NodeDto;

import java.util.ArrayList;
import java.util.List;

public class Node extends Identity{
    private Graph graph;
    private String name;
    private final List<Edge> edgeList = new ArrayList<>();

    public Node() {
    }

    public Node(Graph graph, String name) {
        this.graph = graph;
        this.name = name;

        graph.addNode(this);
    }

    public Node(Graph graph, NodeDto nodeDto) {
        this.graph = graph;
        this.name = nodeDto.name;
        this.setUuid(nodeDto.getUuid());

        graph.addNode(this);
    }

    public static Node build(Graph graph, NodeDto nodeDto) {
        Node node = new Node();
        node.graph = graph;
        node.name = nodeDto.name;
        node.setUuid(nodeDto.getUuid());

        return graph.addNode(node);
    }

    public void addEdge(Edge edge) {
        this.edgeList.add(edge);
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
