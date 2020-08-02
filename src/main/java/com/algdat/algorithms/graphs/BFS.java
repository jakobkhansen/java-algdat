package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

public class BFS {
    public static List<List<Node>> bfs(Node start) {
        List<List<Node>> layers = new ArrayList<>();

        layers.add(new ArrayList<>());

        layers.get(0).add(start);
        start.visited = true;

        for (int i = 0; layers.get(i).size() > 0; i++) {
            layers.add(new ArrayList<>());

            for (Node node : layers.get(i)) {
                for (Node edge : node.edges) {
                    if (!edge.visited) {
                        edge.visited = true;
                        layers.get(i+1).add(edge);
                    }
                }
            }
        }

        return layers.subList(0, layers.size() - 1);
    }

    // Test
    public static void main(String[] args) {
        List<Node> nodes = GraphUtils.generateGraph(5, 0.2F, true, false, 123456789);

        System.out.println(GraphUtils.graphToString(nodes));

        List<List<Node>> bfsLayers = bfs(nodes.get(0));

        System.out.println("BFS: \n" + layersToString(bfsLayers));;
    }

    // toString
    public static String layersToString(List<List<Node>> layers) {
        String ret = "";

        for (int i = 0; i < layers.size(); i++) {
            ret += "Layer " + i + " [ ";

            for (Node node : layers.get(i)) {
                ret += node.id + " ";
            }
            ret += "]\n";
        }
        return ret;
    }
}
