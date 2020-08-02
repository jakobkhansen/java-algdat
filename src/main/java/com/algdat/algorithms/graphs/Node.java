package com.algdat.algorithms.graphs;

import java.util.ArrayList;

public class Node {
    ArrayList<Node> edges = new ArrayList<>();
    boolean visited = false;
    String id;

    public Node(String id) {
        this.id = id;
    }

    public void addEdge(Node node) {
        edges.add(node);
    }

    public void addEdge(Node node, boolean directed) {
        edges.add(node);

        if (!directed) {
            node.addEdge(this);
        }
    }

    public String toString() {
        String ret = "Node: " + id + "\n" + "Edges: [ ";
        for (Node node : edges) {
            ret += node.id + " ";
        }
        return ret + "]\n";
    }
}
