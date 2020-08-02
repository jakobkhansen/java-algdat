package com.algdat.algorithms.graphs;

import java.util.ArrayList;

public class DFS {
    // Returns the order of nodes visited in DFS order
    public static ArrayList<Node> dfsOrder(Node start) {
        ArrayList<Node> order = new ArrayList<>();
        dfsRecursive(order, start);
        return order;
    }

    public static void dfsRecursive(ArrayList<Node> order, Node current) {
        order.add(current);
        current.visited = true;

        for (Node edge : current.edges) {
            if (!edge.visited) {
                dfsRecursive(order, edge);
            }
        }
    }

    // Test
    public static void main(String[] args) {
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");

        node1.addEdge(node2);
        node2.addEdge(node3);
        node2.addEdge(node4);
        node4.addEdge(node5);

        ArrayList<Node> order = dfsOrder(node1);

        for (Node node : order) {
            System.out.println(node.toString());
        }
    }


}
