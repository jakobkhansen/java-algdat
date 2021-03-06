package com.algdat.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.algdat.interfaces.GraphNodeBase;

public class GraphGenerator<T extends GraphNodeBase<T>> {

    private Random numGen;
    private int numNodes;
    private float sparsity; 
    int minWeight;
    int maxWeight;
    private boolean connected;
    private boolean directed;

    public GraphGenerator() {}

    public List<T> generateGraph(
            Class<T> nodeType,
                int numNodes, 
                float sparsity, 
                int minWeight,
                int maxWeight,
                boolean connected, 
                boolean directed,
                long seed
            ) {
        this.numNodes = numNodes;
        this.sparsity = sparsity;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.connected = connected;
        this.directed = directed;

        numGen = new Random(seed);

        List<T> nodes = new ArrayList<>();

        buildNodes(nodeType, nodes);

        buildEdges(nodes);

        return nodes;
    }

    public List<T> generateGraph(
            Class<T> nodeType,
            int numNodes, 
            float sparsity, 
            boolean connected, 
            boolean directed,
            long seed
    ) {
        return generateGraph(nodeType, numNodes, sparsity, 0, 0, connected, directed, seed);
    }

    private void buildNodes(Class<T> nodeType, List<T> nodes) {
        for (int i = 0; i < numNodes; i++) {
            try {
                T newNode = nodeType.getConstructor(String.class).newInstance("" + i);
                nodes.add(newNode);


                if (connected && i > 0) {
                    int randomEdge = randomNumber(0, i-1);
                    int weight = randomNumber(minWeight, maxWeight);

                    if (directed) {
                        nodes.get(i).addEdge(nodes.get(randomEdge), weight);
                    } else {
                        nodes.get(i).addEdge(nodes.get(randomEdge), weight);
                        nodes.get(randomEdge).addEdge(nodes.get(i), weight);
                    }
                }

            } catch (Exception e) {
                System.out.println("Error handling LOL");
            }
        }
    }

    private void buildEdges(List<T> nodes) {
        for (T node : nodes) {
            for (T potentialEdge : nodes) {
                if (randomBoolean(sparsity)) {
                    if (node != potentialEdge && !node.containsEdge(potentialEdge)) {
                        int weight = randomNumber(minWeight, maxWeight);
                        if (directed) {
                            node.addEdge(potentialEdge, weight);
                        } else {
                            node.addEdge(potentialEdge, weight);
                            potentialEdge.addEdge(node, weight);
                        }
                    }
                }
            }
        }
    }

    private int randomNumber(int min, int max) {
        return numGen.nextInt(max+1 - min) + min;
    }

    private boolean randomBoolean(float percentage) {
        return numGen.nextFloat() < percentage;
    }

}
