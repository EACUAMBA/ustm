import graph.Edge;
import graph.Graph;
import graph.Node;
import load_from_json.GraphDto;
import load_from_json.GraphDtoToGraph;
import load_from_json.LoaderHelper;

import static java.util.Objects.isNull;

public class LoadGraph {
    public static void loadWithFakeDataGraph(Graph graph) {
        if (isNull(graph)) {
            return;
        }

        Node a = new Node(graph, "A");
        Node b = new Node(graph, "B");
        Node c = new Node(graph, "C");
        Node d = new Node(graph, "D");
        Node e = new Node(graph, "E");
        Node f = new Node(graph, "F");
        Node g = new Node(graph, "G");
        Node h = new Node(graph, "H");
        Node i = new Node(graph, "I");

        new Edge(graph, 1D, a, b);
        new Edge(graph, 1D, a, c);
        new Edge(graph, 1D, c, d);
        new Edge(graph, 1D, b, d);
        new Edge(graph, 1D, d, f);
        new Edge(graph, 1D, d, e);
        new Edge(graph, 1D, f, g);
        new Edge(graph, 1D, g, h);
        new Edge(graph, 1D, e, h);
        new Edge(graph, 1D, h, i);
    }

    public static void loadRomeniaData(Graph graph) {
        if (isNull(graph)) {
            return;
        }

        Node orades = new Node(graph, "Orades");
        Node zerind = new Node(graph, "Zerind");
        Node arad = new Node(graph, "Arad");
        Node sibiu = new Node(graph, "Sibiu");
        Node timisoara = new Node(graph, "Timisoara");
        Node lugoj = new Node(graph, "Lugoj");
        Node mehadia = new Node(graph, "Mehadia");
        Node dobreta = new Node(graph, "Dobreta");
        Node fagaras = new Node(graph, "Fagaras");
        Node rimnicuVilcea = new Node(graph, "Rimnicu Vilcea");
        Node pitesti = new Node(graph, "Pitesti");
        Node craiova = new Node(graph, "Craiova");
        Node bucharest = new Node(graph, "Bucharest");
        Node giurgiu = new Node(graph, "Giurgiu");
        Node urziceni = new Node(graph, "Urziceni");
        Node vaslui = new Node(graph, "Vaslui");
        Node hirsova = new Node(graph, "Hirsova");
        Node eforie = new Node(graph, "Eforie");
        Node lasi = new Node(graph, "Lasi");
        Node neamt = new Node(graph, "Neamt");

        graph
                .addEdge(71D, orades, zerind)
                .addEdge(151D, orades, sibiu)
                .addEdge(140D, arad, sibiu)
                .addEdge(75D, arad, zerind)
                .addEdge(118D, arad, timisoara)
                .addEdge(99D, sibiu, fagaras)
                .addEdge(99D, sibiu, rimnicuVilcea)
                .addEdge(211, fagaras, bucharest)
                .addEdge(90, giurgiu, bucharest)
                .addEdge(97D, rimnicuVilcea, pitesti)
                .addEdge(146D, rimnicuVilcea, craiova)
                .addEdge(138D, pitesti, craiova)
                .addEdge(120, dobreta, craiova)
                .addEdge(101D, pitesti, bucharest)
                .addEdge(85, urziceni, bucharest)
                .addEdge(142, urziceni, vaslui)
                .addEdge(98, urziceni, hirsova)
                .addEdge(86, hirsova, eforie)
                .addEdge(92, vaslui, lasi)
                .addEdge(87, lasi, neamt)
                .addEdge(75, mehadia, dobreta)
                .addEdge(70, lugoj, mehadia)
                .addEdge(111, lugoj, timisoara)
        ;
    }

    public static void loadFromJson(Graph graph){
        GraphDto graphDto = LoaderHelper.graphDto();
        GraphDtoToGraph.mapTpGraph(graphDto, graph);
    }
}
