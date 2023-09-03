package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

public class Route extends Identity {

    private String name = UUID.randomUUID().toString();
    private final List<Node> nodeList = new ArrayList<>();
    private final List<Edge> edgeList = new ArrayList<>();

    public Route() {
    }

    public Route(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void addEdge(Edge edge) {
        boolean contains = this.edgeList.contains(edge);
        if (!contains) {
            this.edgeList.add(edge);
        }
    }

    public Double getWeight() {
        return this
                .edgeList
                .stream()
                .mapToDouble(Edge::getWeight)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilderForNodeList = new StringBuilder();
        ListIterator<Node> nodeListIterator = nodeList.listIterator();
        while (nodeListIterator.hasNext()) {
            Node next = nodeListIterator.next();
            stringBuilderForNodeList
                    .append(next.toString());

            if (nodeListIterator.hasNext()) {
                stringBuilderForNodeList.append(", ");
            }
        }

        StringBuilder stringBuilderForEdgeList = new StringBuilder();
        ListIterator<Edge> edgeListIterator = edgeList.listIterator();
        while (edgeListIterator.hasNext()) {
            Edge next = edgeListIterator.next();
            stringBuilderForEdgeList
                    .append(next.toString());

            if (edgeListIterator.hasNext()) {
                stringBuilderForEdgeList.append(", ");
            }
        }


        String string =
                """
                        %n
                        Route %s
                        Route of nodes {%s}
                        Route of edges {%s}
                        Route weight is %.2f
                        """;
        return String.format(string, this.name, stringBuilderForNodeList, stringBuilderForEdgeList, this.getWeight());
    }

    public boolean notHaveIBeenHereBefore(Node node) {
        return !this.nodeList.contains(node);
    }

    public void addNode(Node node) {
        if(!this.nodeList.contains(node))
            this.nodeList.add(node);
    }

    public Route cloneWithPartial() {
        Route route = new Route();
        this.getNodeList().forEach(route::addNode);
        this.getEdgeList().forEach(route::addEdge);
        return route;
    }

    public Route joinWithPartial(PartialRoute partialRoute) {
        Route route = new Route();

        this.getNodeList().forEach(route::addNode);
        partialRoute.getNodeList().forEach(route::addNode);

        this.getEdgeList().forEach(route::addEdge);
        partialRoute.getEdgeList().forEach(route::addEdge);
        return route;
    }
}
