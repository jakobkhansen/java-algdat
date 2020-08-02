package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphUtils {
    private static Random numGen;
    public static List<Node> generateGraph(
                int numNodes, 
                float sparsity, 
                boolean connected, 
                boolean directed,
                long seed
            ) {

        numGen = new Random(seed);

        List<Node> nodes = new ArrayList<>();

        buildNodes(nodes, numNodes, connected, directed);

        buildEdges(nodes, sparsity, directed);

        return nodes;
    }

    static void buildNodes(List<Node> nodes, int numNodes, boolean connected, boolean directed) {
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new Node("" + i));

            if (connected && i > 0) {
                int randomEdge = randomNumber(0, i);
                nodes.get(i).addEdge(nodes.get(randomEdge), directed);
            }
        }
    }

    static void buildEdges(List<Node> nodes, float sparsity, boolean directed) {
        for (Node node : nodes) {
            for (Node potentialEdge : nodes) {
                if (node != potentialEdge && !node.edges.contains(potentialEdge)) {
                    if (randomBoolean(sparsity)) {
                        node.addEdge(potentialEdge, directed);
                    }
                }
            }
        }
    }

    static int randomNumber(int min, int max) {
        return numGen.nextInt(max - min) + min;
    }

    static boolean randomBoolean(float percentage) {
        return numGen.nextFloat() < percentage;
    }

    public static void resetVisited(List<Node> nodes) {
        for (Node node : nodes) {
            node.visited = false;
        }
    }

    public static String graphToString(List<Node> nodes) {
        String ret = "Graph: \n";

        for (Node node : nodes) {
            ret += node.toString() + "\n";
        }

        return ret;
    }
}
