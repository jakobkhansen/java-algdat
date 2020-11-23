package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.algdat.interfaces.GraphNodeUnweighted;
import com.algdat.utils.GraphGenerator;
import com.algdat.utils.GraphUtils;

public class IterativeDeepening {
    public static class Node implements GraphNodeUnweighted<Node> {
        List<Node> edges = new ArrayList<>();
        boolean isGoal = false;
        String id;

        public Node(String id) {
            this.id = id;
        }

        @Override
        public boolean containsEdge(Node node) {
            return edges.contains(node);
        }

        @Override
        public List<Node> getEdges() {
            return edges;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public void addEdge(Node node) {
            edges.add(node);
        }
    }

    public static List<Node> iterativeDeepeningOrder(Node start) {
        List<Node> order = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            HashMap<Node, Boolean> visited = new HashMap<>();
            if (deepeningRecursive(start, order, 0, i, visited)) {
                break;
            }
        }

        return order;
    }

    public static boolean deepeningRecursive(Node v, List<Node> order, int depth, int maxDepth, HashMap<Node, Boolean> visited) {
        if (depth >= maxDepth) {
            return false;
        }

        order.add(v);
        visited.put(v, true);

        if (v.isGoal) {
            return true;
        }

        for (Node edge : v.getEdges()) {
            if (!visited.getOrDefault(edge, false)) {
                if (deepeningRecursive(edge, order, depth+1, maxDepth, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        GraphGenerator<Node> generator = new GraphGenerator<>();
        List<Node> graph = generator.generateGraph(Node.class, 30, 0.01F, true, false, 1234567890);

        System.out.println(GraphUtils.unweightedGraphToGraphML(graph, false));
        System.out.println(GraphUtils.unweightedGraphToStringDetailed(graph));

        graph.get(16).isGoal = true;

        List<Node> iterativeDeepeningOrder = iterativeDeepeningOrder(graph.get(0));

        System.out.println(GraphUtils.nodesToStringSimple(iterativeDeepeningOrder));
    }
}
