package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algdat.interfaces.GraphNodeWeighted;
import com.algdat.utils.GraphGenerator;
import com.algdat.utils.GraphUtils;

public class Prims {
    public static class Node implements GraphNodeWeighted<Node> {
        String id;
        int smallestWeight = Integer.MAX_VALUE;
        Node smallestEdge;
        Map<Node, Integer> edges = new HashMap<>();

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
    }


    // Returns a new graph representing the minimum spanning tree of the parameter graph
    // Because it is copying a graph and adding the minimum edges, this is very unclean code.
    public static List<Node> minimumSpanningTree(List<Node> graph) {
        List<Node> mSpanningTree = new ArrayList<>();
        List<Node> queue = new ArrayList<>();

        HashMap<Node, Node> originalToNewMap = new HashMap<>();

        for (Node node : graph) {
            queue.add(node);
            Node spanningTreeNode = new Node(node.id);
            mSpanningTree.add(spanningTreeNode);
            originalToNewMap.put(node, spanningTreeNode);
        }

        while (!queue.isEmpty()) {
            int smallestEdgeNode = findSmallest(queue);
            Node node = queue.remove(smallestEdgeNode);

            Node newNode = originalToNewMap.get(node);

            // Disgusting
            // Adds the smallest edge to the node both ways, to the spanning tree graph.
            if (node.smallestWeight != Integer.MAX_VALUE) {
                newNode.addEdge(originalToNewMap.get(node.smallestEdge), node.smallestWeight);
                originalToNewMap.get(node.smallestEdge).addEdge(newNode, node.smallestWeight);
            }

            for (Node edge : node.edges.keySet()) {
                if (queue.contains(edge) && node.edges.get(edge) < edge.smallestWeight) {
                    edge.smallestEdge = node;
                    edge.smallestWeight = node.edges.get(edge);
                }
            }
        }

        return mSpanningTree;
    }

    public static int findSmallest(List<Node> nodes) {
        int smallestVal = nodes.get(0).smallestWeight;
        int smallestIndex = 0;

        for (int i = 1; i < nodes.size(); i++) {
            if (nodes.get(i).smallestWeight < smallestVal) {
                smallestVal = nodes.get(i).smallestWeight;
                smallestIndex = i;
            }
        }

        return smallestIndex;
    }


    public static void main(String[] args) {
        GraphGenerator<Node> generator = new GraphGenerator<Node>();
        List<Node> graph = generator.generateGraph(Node.class, 10, 0.05F, 0, 20, true, false, 123456789);

        System.out.println(GraphUtils.weightedGraphToStringDetailed(graph));

        //System.out.println(GraphUtils.weightedGraphToGraphML(graph, false));

        List<Node> mSpanningTree = minimumSpanningTree(graph);

        System.out.println("\n\nMinimum spanning tree");
        System.out.println(GraphUtils.weightedGraphToStringDetailed(mSpanningTree));
    }
}
