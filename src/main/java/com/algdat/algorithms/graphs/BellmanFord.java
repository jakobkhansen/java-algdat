package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

import com.algdat.datastructures.graphs.Node;
import com.algdat.utils.GraphUtils;

public class BellmanFord {
    public static List<Node> shortestPathBetween(List<Node> nodes, Node start, Node finish) {
        List<Node> path = new ArrayList<>();

        for (Node node : nodes) {
            node.shortestPathValue = Integer.MAX_VALUE;
        }

        start.shortestPathValue = 0;

        // Relax edges
        for (int i = 0; i < nodes.size()-1; i++) {
            for (Node node : nodes) {
                for (Node edge : node.edges.keySet()) {
                    int potentialShortest = node.shortestPathValue + node.edges.get(edge);
                    if (node.shortestPathValue != Integer.MAX_VALUE && potentialShortest < edge.shortestPathValue && node.previous != edge) {
                        edge.shortestPathValue = potentialShortest;
                        edge.previous = node;
                    }
                }
            }
        }

        for (Node node : nodes) {
            System.out.println(node.id + " " + node.shortestPathValue);
        }

        // Check for negative cycle
        for (Node node : nodes) {
            for (Node edge : node.edges.keySet()) {
                int potentialShortest = node.shortestPathValue + node.edges.get(edge);

                System.out.println(edge.id);
                if (node.shortestPathValue != Integer.MAX_VALUE && potentialShortest < edge.shortestPathValue && node.previous != edge) {
                    System.out.println("Negative cycle");
                    return null;
                }
            }
        }

        // Find shortest path
        if (finish.previous != null) {
            path.add(finish);

            Node current = finish;

            while (current.previous != null) {
                path.add(current.previous);
                current = current.previous;
            }
        }


        return path;
    }


    public static void main(String[] args) {
        List<Node> graph = GraphUtils.generateGraph(10, 0.05F, -2, 10, true, false, 1234567);

        System.out.println(GraphUtils.nodesToStringDetailed(graph));

        System.out.println(GraphUtils.graphToGraphML(graph, false));

        System.out.println("Bellman Ford:");
        List<Node> path = shortestPathBetween(graph, graph.get(9), graph.get(5));

        if (path != null) {
            System.out.println(GraphUtils.nodesToStringSimpleReversed(path));
        }
    }

}
