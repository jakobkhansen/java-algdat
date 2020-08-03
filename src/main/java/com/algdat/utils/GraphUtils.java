package com.algdat.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.algdat.datastructures.graphs.Node;

public class GraphUtils {
    private static Random numGen;
    public static List<Node> generateGraph(
                int numNodes, 
                float sparsity, 
                int maxWeight,
                boolean connected, 
                boolean directed,
                long seed
            ) {

        numGen = new Random(seed);

        List<Node> nodes = new ArrayList<>();

        buildNodes(nodes, numNodes, maxWeight, connected, directed);

        buildEdges(nodes, maxWeight, sparsity, directed);

        return nodes;
    }

    static void buildNodes(List<Node> nodes, int numNodes, int maxWeight, boolean connected, boolean directed) {
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new Node("" + i));

            if (connected && i > 0) {
                int randomEdge = randomNumber(0, i);
                int weight = randomNumber(1, maxWeight+1);

                nodes.get(i).addEdge(nodes.get(randomEdge), directed, weight);
            }
        }
    }

    static void buildEdges(List<Node> nodes, int maxWeight, float sparsity, boolean directed) {
        for (Node node : nodes) {
            for (Node potentialEdge : nodes) {
                if (node != potentialEdge && !node.edges.keySet().contains(potentialEdge)) {
                    if (randomBoolean(sparsity)) {
                        int weight = randomNumber(1, maxWeight+1);
                        node.addEdge(potentialEdge, directed, weight);
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

    public static String nodesToStringDetailed(List<Node> nodes) {
        String ret = "Graph: \n";

        for (Node node : nodes) {
            ret += node.toStringDetailed() + "\n";
        }

        return ret;
    }

    public static String nodesToStringSimple(List<Node> nodes) {
        String ret = "[ ";

        for (Node node : nodes) {
            ret += node.toString() + " ";
        }

        return ret + "]";
    }

    public static String nodesToStringSimpleReversed(List<Node> nodes) {
        String ret = " ]";

        for (Node node : nodes) {
            ret = " " + node.id + ret;
        }

        return "[" + ret;
    }

    // Use this to visualize graph on https://graphonline.ru/en/
    public static String graphToGraphML(List<Node> nodes, boolean directed) {
        String xml = "<?xml version='1.0' encoding='UTF-8'?><graphml><graph id='Graph' uidGraph='0' uidEdge='0'>";

        for (Node node : nodes) {
            int x = randomNumber(0, 500);
            int y = randomNumber(0, 500);
            xml += "<node positionX='" + x + "' positionY='" + y + "' id='" + node.id + "' mainText='" + node.id + "' upText=''/>";
        }

        int edgeId = 10002;
        for (Node node : nodes) {
            for (Node edge : node.edges.keySet()) {
                if (directed || Integer.parseInt(node.id) < Integer.parseInt(edge.id)) {
                    xml += "<edge source='" + node.id + "' target='" + edge.id + "' isDirect='" + Boolean.toString(directed) + "' weight='" + node.edges.get(edge) + "' useWeight='true' id='" + edgeId + "' text='' upText='' arrayStyleStart='' arrayStyleFinish='' model_width='4' model_type='0' model_curvedValue='0.1'/>";
                    edgeId++;
                }
            }
        }

        xml += "</graph></graphml>";

        return xml;
    }
}
