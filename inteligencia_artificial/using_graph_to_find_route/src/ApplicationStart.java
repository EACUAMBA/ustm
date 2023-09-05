import graph.Graph;
import graph.Node;
import graph.OriginTarget;
import graph.Route;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ApplicationStart {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Using graph to find routes application, Edilson Alexandre Cuamba.");

        Graph graph = new Graph();
        LoadGraph.loadRomeniaData(graph);
        //LoadGraph.saveAsJson(graph);
        graph.printInstance();

        Node origin = getCurrentNode(graph);
        Node target = getTargetNode(graph);

        long currentTimeMillisStart = System.currentTimeMillis();

        OriginTarget originTarget = new OriginTarget();
        originTarget.setOrigin(origin);
        originTarget.setTarget(target);

        List<Route> routesList = graph.findRoutes(originTarget);
        routesList.forEach(System.out::println);

        System.out.println("Threads Used:");
        System.out.println("This run used " + Graph.THREAD_USED_LIST.size() + " threads");
        Graph.THREAD_USED_LIST.stream()
                .collect(Collectors.groupingBy(String::trim))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach((entry) -> {
                    System.out.print(entry.getKey());
                    System.out.print(" - ");
                    System.out.print(entry.getValue().size());
                    System.out.println();
                });

        long currentTimeMillisEnd = System.currentTimeMillis();

        long duration = currentTimeMillisEnd - currentTimeMillisStart;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        if (seconds != 0) {
            System.out.println("This task took " + seconds + " seconds");
        } else {
            System.out.println("This task took " + duration + " milliseconds");
        }
        System.exit(0);
    }

    public static Node getCurrentNode(Graph graph) {
        do {
            try {
                System.out.println("Select your current location:");
                List<String> nodeNamesForCurrentLocationList = IntStream
                        .range(0, graph.getNodeList().size())
                        .mapToObj(value -> value + " - " + graph
                                .getNodeList()
                                .get(value))
                        .toList();

                StringBuilder nodeNamesForCurrentLocation = new StringBuilder();
                ListIterator<String> nodeNamesForCurrentLocationIterator = nodeNamesForCurrentLocationList
                        .listIterator();
                while (nodeNamesForCurrentLocationIterator
                        .hasNext()) {
                    String next = nodeNamesForCurrentLocationIterator
                            .next();
                    nodeNamesForCurrentLocation.append(next);
                    if (nodeNamesForCurrentLocationIterator.hasNext()) {
                        nodeNamesForCurrentLocation.append("\n");
                    }
                }
                System.out.println(nodeNamesForCurrentLocation);
                System.out.print("Answer: ");
                int currentLocationNodeIndex = SCANNER.nextInt();
                Node currentLocationNode = graph
                        .getNodeList()
                        .get(currentLocationNodeIndex);
                System.out.printf("Your current location is %s.%n", currentLocationNode);
                return currentLocationNode;
            } catch (Exception e) {

            }
        } while (true);
    }

    public static Node getTargetNode(Graph graph) {
        do {
            try {
                System.out.println("Select your target location:");
                List<String> nodeNamesForTargetLocationList = IntStream
                        .range(0, graph.getNodeList().size())
                        .mapToObj(value -> value + " - " + graph
                                .getNodeList()
                                .get(value))
                        .toList();

                StringBuilder nodeNamesForTargetLocation = new StringBuilder();
                ListIterator<String> nodeNamesForTargetLocationIterator = nodeNamesForTargetLocationList
                        .listIterator();
                while (nodeNamesForTargetLocationIterator
                        .hasNext()) {
                    String next = nodeNamesForTargetLocationIterator.next();
                    nodeNamesForTargetLocation.append(next);
                    if (nodeNamesForTargetLocationIterator.hasNext()) {
                        nodeNamesForTargetLocation.append("\n");
                    }
                }
                System.out.println(nodeNamesForTargetLocation);
                System.out.print("Answer: ");
                int targetLocationNodeIndex = SCANNER.nextInt();
                Node targetLocationNode = graph
                        .getNodeList()
                        .get(targetLocationNodeIndex);
                System.out.printf("Your target location is %s.%n", targetLocationNode);
                return targetLocationNode;
            } catch (Exception e) {

            }
        } while (true);
    }
}
