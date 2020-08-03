package com.algdat.datastructures.graphs;


import java.util.HashMap;

public class Node {
    // Edges
    public HashMap<Node, Integer> edges = new HashMap<>();

    // DFS and BFS
    public boolean visited = false;

    // Dijkstra
    public Node previous = null;
    public boolean finished = false;
    public int shortestPathValue = Integer.MAX_VALUE;

    public String id;

    public Node(String id) {
        this.id = id;
    }

    public void addEdge(Node node) {
        edges.put(node, 0);
    }

    public void addEdge(Node node, boolean directed) {
        edges.put(node, 0);

        if (!directed) {
            node.addEdge(this);
        }
    }

    public void addEdge(Node node, int weight) {
        edges.put(node, weight);
    }

    public void addEdge(Node node, boolean directed, int weight) {
        edges.put(node, weight);

        if (!directed) {
            node.addEdge(this, weight);
        }
    }

    public String toString() {
        return id;
    }

    public String toStringDetailed() {
        String ret = "Node: " + id + "\n" + "Edges (id, weight): [ ";
        for (Node node : edges.keySet()) {
            ret += "(" + node.id + ", " + edges.get(node) + ") ";
        }
        return ret + "]\n";
    }
}
