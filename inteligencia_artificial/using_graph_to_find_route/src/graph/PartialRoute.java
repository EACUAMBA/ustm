package graph;

public class PartialRoute extends Route{
    public PartialRoute cloneWithPartial() {
        PartialRoute partialRoute = new PartialRoute();

        this.getNodeList().forEach(partialRoute::addNode);

        this.getEdgeList().forEach(partialRoute::addEdge);
        return partialRoute;
    }

    public PartialRoute joinWithPartial(PartialRoute otherPartialRoute) {
        PartialRoute partialRoute = new PartialRoute();

        this.getNodeList().forEach(partialRoute::addNode);
        otherPartialRoute.getNodeList().forEach(partialRoute::addNode);

        this.getEdgeList().forEach(partialRoute::addEdge);
        otherPartialRoute.getEdgeList().forEach(partialRoute::addEdge);
        return partialRoute;
    }
}
