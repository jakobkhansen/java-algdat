package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

import com.algdat.datastructures.graphs.Node;
import com.algdat.utils.GraphUtils;

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

        for (Node edge : current.edges.keySet()) {
            if (!edge.visited) {
                dfsRecursive(order, edge);
            }
        }
    }

    // Test
    public static void main(String[] args) {
        List<Node> graph = GraphUtils.generateGraph(10, 0.2F, 0, true, false, 123456789);

        ArrayList<Node> order = dfsOrder(graph.get(0));

        System.out.println(GraphUtils.nodesToStringDetailed(graph));

        System.out.println("DFS:");
        for (Node node : order) {
            System.out.print(node.id + " ");
        }
    }


}
