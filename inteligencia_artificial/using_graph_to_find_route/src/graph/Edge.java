package graph;

import java.util.Objects;

import static java.util.Objects.isNull;

public class Edge extends Identity {
    private Graph graph;
    private String name;
    private Double weight;
    private Node leftNode;
    private Node rightNode;
    //private boolean oneWay;
    //private OneWayTime oneWayTime;

    public Edge() {
    }

    public Edge(Graph graph, Double weight, Node leftNode, Node rightNode) {
        this.graph = graph;
        this.name = leftNode.getName() + " <-> " + rightNode.getName();
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.weight = weight;

        this.leftNode.addEdge(this);
        this.rightNode.addEdge(this);
        graph.addEdge(this);
    }

    public Edge(Graph graph, String uuid,String name, Double weight, Node leftNode, Node rightNode) {
        this.graph = graph;
        this.setUuid(uuid);
        if (isNull(name) || name.isEmpty()) {
            this.name = leftNode.getName() + " <=> " + rightNode.getName();
        } else {
            this.name = name + " (" + leftNode.getName() + " <=> " + rightNode.getName() + ")";
        }
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.weight = weight;

        this.leftNode.addEdge(this);
        this.rightNode.addEdge(this);
        graph.addEdge(this);
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Edge edge = (Edge) o;

        if ((Objects.equals(this.leftNode, edge.leftNode)
                && Objects.equals(this.rightNode, edge.rightNode)) ||
                (Objects.equals(this.leftNode, edge.rightNode)
                        && Objects.equals(this.rightNode, edge.leftNode))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), leftNode, rightNode);
    }
}
