package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.algdat.interfaces.GraphNodeWeighted;
import com.algdat.utils.GraphGenerator;
import com.algdat.utils.GraphUtils;

public class Kruskals {
    public static class Node implements GraphNodeWeighted<Node> {
        public HashMap<Node, Integer> edges = new HashMap<>();
        public String id;

        public Node(String id) {
            this.id = id;
        }

        @Override
        public void addEdge(Node node) {
            edges.put(node, 0);
        }

        @Override
        public void addEdge(Node node, int weight) {
            edges.put(node, weight);
        }

        @Override
        public boolean containsEdge(Node node) {
            return edges.containsKey(node);
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public Map<Node, Integer> getEdges() {
            return edges;
        }

        public List<Edge> getEdgesObj() {
            List<Edge> edgeList = new LinkedList<>();
            for (Node node : edges.keySet()) {
                edgeList.add(new Edge(this, node, edges.get(node)));
            }

            return edgeList;
        }
    }

    public static class Edge {
        Node from, to;
        int weight = 0;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static List<Node> minimumSpanningTree(List<Node> graph) {
        // New list of nodes which will be returned as the spanning tree
        List<Node> spanningTree = new ArrayList<>();

        // A mapping from the old nodes to the spanning tree
        Map<Node, Node> mapping = new HashMap<>();

        // All edges
        LinkedList<Edge> edges = new LinkedList<>();

        // Edges in spanning tree
        LinkedList<Edge> spanningTreeEdges = new LinkedList<>();

        HashSet<HashSet<Node>> forests = new HashSet<>();

        for (Node node : graph) {
            HashSet<Node> newForest = new HashSet<>();
            newForest.add(node);
            forests.add(newForest);
        }

        // Get all edges in list and create mapping

        for (Node node : graph) {
            for (Edge edge : node.getEdgesObj()) {
                edges.add(edge);
            }

            Node newNode = new Node(node.id);
            mapping.put(node, newNode);
            spanningTree.add(newNode);
        }

        // Sort all edges
        edges.sort(new Comparator<Edge>(){

            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            } 
        });

        while (!edges.isEmpty()) {
            Edge edge = edges.removeFirst();


            HashSet<Node> forest1 = null;
            HashSet<Node> forest2 = null;

            for (HashSet<Node> forest : forests) {
                if (forest.contains(edge.from)) {
                    forest1 = forest;
                }

                if (forest.contains(edge.to)) {
                    forest2 = forest;
                }
            }

            if (forest1 != forest2) {
                spanningTreeEdges.add(edge);
                forest1.addAll(forest2);
                forests.remove(forest2);
            }

        }

        for (Edge edge : spanningTreeEdges) {
            Node spanningNodeFrom = mapping.get(edge.from);
            Node spanningNodeTo = mapping.get(edge.to);

            spanningNodeFrom.addEdge(spanningNodeTo, edge.weight);
            spanningNodeTo.addEdge(spanningNodeFrom, edge.weight);
        }

        return spanningTree;
    }


    public static void main(String[] args) {
        GraphGenerator<Node> generator = new GraphGenerator<>();
        List<Node> graph = generator.generateGraph(Node.class, 10, 0.01F, 1, 20, true, false, 123456789);

        System.out.println(GraphUtils.weightedGraphToGraphML(graph, false));

        System.out.println("\n\n");

        List<Node> spanningTree = minimumSpanningTree(graph);

        System.out.println(GraphUtils.weightedGraphToGraphML(spanningTree, false));

        System.out.println(GraphUtils.weightedGraphToStringDetailed(spanningTree));
    }
}
