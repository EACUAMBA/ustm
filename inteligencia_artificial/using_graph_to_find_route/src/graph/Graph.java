package graph;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Graph extends Identity {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    //this code makes this app not ok to use in a web application
    public static final List<String> THREAD_USED_LIST = new ArrayList<>();
    private final List<Node> nodeList = new ArrayList<>();
    private final List<Edge> edgeList = new ArrayList<>();

    public Graph() {
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public List<Route> findRoutes(OriginTarget originTarget) {
        Node fromNode = originTarget.getOrigin();
        Node toNode = originTarget.getTarget();

        System.out.printf("%n%nFinding routes from %s to %s.%n", fromNode, toNode);

        final List<Route> routeList = new ArrayList<>();

        PartialRoute partialRoute = new PartialRoute();
        partialRoute.addNode(originTarget.getOrigin());
        List<PartialRoute> partialRoutes = findPartialRoutes(partialRoute, originTarget, fromNode);
        partialRoutes.stream().map(pr -> {
                    Route route = new Route();
                    return route.joinWithPartial(pr);
                })
                .forEach(routeList::add);

        System.out.printf("%n%nFound %d routes from %s to %s.%n", routeList.size(), fromNode, toNode);
        return routeList
                .stream()
                .sorted(Comparator.comparing(Route::getWeight))
                .toList();
    }

    private List<PartialRoute> findPartialRoutes(PartialRoute partialRoute, OriginTarget originTarget, Node node) {
        final List<PartialRoute> partialRouteList = new ArrayList<>();
        List<Edge> nodeEdgeList = node.getEdgeList();

        for (Edge edge : nodeEdgeList) {
            Node leftNode = edge.getLeftNode();
            PartialRoute leftPartialRoute = partialRoute.cloneWithPartial();

            Node rightNode = edge.getRightNode();
            PartialRoute rightPartialRoute = partialRoute.cloneWithPartial();

//
//            Future<List<PartialRoute>> leftFuture = EXECUTOR_SERVICE.submit(() -> checkNode(originTarget, edge, leftNode, leftPartialRoute));
//
//            Future<List<PartialRoute>> rightFuture = EXECUTOR_SERVICE.submit(() -> checkNode(originTarget, edge, rightNode, rightPartialRoute));
//
//            try {
//                partialRouteList.addAll(leftFuture.get());
//                partialRouteList.addAll(rightFuture.get());
//            } catch (InterruptedException | ExecutionException e) {
//                throw new RuntimeException(e);
//            }

            partialRouteList.addAll(checkNode(originTarget, edge, leftNode, leftPartialRoute));
            partialRouteList.addAll(checkNode(originTarget, edge, rightNode, rightPartialRoute));
        }

        return partialRouteList.stream()
                .map(partialRoute::joinWithPartial)
                .collect(Collectors.toList());
    }

    private List<PartialRoute> checkNode(OriginTarget originTarget, Edge edge, Node leftNode, PartialRoute leftPartialRoute) {
        THREAD_USED_LIST.add(Thread.currentThread().getName());

        final List<PartialRoute> partialRouteList = new ArrayList<>();
        if (leftPartialRoute.notHaveIBeenHereBefore(leftNode)
                && !Objects.equals(leftNode, originTarget.getOrigin())
                && !Objects.equals(leftNode, originTarget.getTarget())
        ) {
            leftPartialRoute.addEdge(edge);
            leftPartialRoute.addNode(leftNode);
            partialRouteList.addAll(findPartialRoutes(leftPartialRoute, originTarget, leftNode));
        }

        if (Objects.equals(leftNode, originTarget.getTarget())) {
            leftPartialRoute.addEdge(edge);
            leftPartialRoute.addNode(leftNode);
            partialRouteList.add(leftPartialRoute);
        }
        return partialRouteList;
    }

    public void addEdge(Edge edge) {
        if (!this.edgeList.contains(edge))
            this.edgeList.add(edge);
    }

    public Graph addEdge(
            double weight,
            Node leftNode,
            Node rightNode
    ) {
        this.addEdge(new Edge(this, weight, leftNode, rightNode));
        return this;
    }

    public Graph addEdge(
            String uuid,
            double weight,
            String name,
            Node leftNode,
            Node rightNode
    ) {
        this.addEdge(new Edge(this, uuid, name, weight, leftNode, rightNode));
        return this;
    }

    public Node addNode(Node node) {
        if (!this.nodeList.contains(node))
            this.nodeList.add(node);

        return this.nodeList.get(this.nodeList.indexOf(node));
    }

    public Graph addNode(
            String name
    ) {
        this.addNode(new Node(this, name));
        return this;
    }

    public void printInstance() {
        StringBuilder nodeAvailableStringBuilder = new StringBuilder();
        ListIterator<Node> nodeNamesForCurrentLocationIterator = this.nodeList
                .listIterator();
        while (nodeNamesForCurrentLocationIterator.hasNext()) {
            Node node = nodeNamesForCurrentLocationIterator.next();
            nodeAvailableStringBuilder.append(node.toString());
            if (nodeNamesForCurrentLocationIterator.hasNext()) {
                nodeAvailableStringBuilder.append("\n");
            }
        }
        System.out.println("\nNodes Available (" + this.nodeList.size() + "): \n" + nodeAvailableStringBuilder + "\n");

        StringBuilder edgeAvailableStringBuilder = new StringBuilder();
        ListIterator<Edge> edgeNamesForCurrentLocationIterator = this.getEdgeList()
                .listIterator();
        while (edgeNamesForCurrentLocationIterator.hasNext()) {
            Edge node = edgeNamesForCurrentLocationIterator.next();
            edgeAvailableStringBuilder.append(node.toString());
            if (edgeNamesForCurrentLocationIterator.hasNext()) {
                edgeAvailableStringBuilder.append("\n");
            }
        }
        System.out.println("Edges Available (" + this.edgeList.size() + "): \n" + edgeAvailableStringBuilder + "\n");
    }
}
