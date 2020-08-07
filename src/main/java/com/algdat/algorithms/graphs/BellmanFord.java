package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algdat.interfaces.GraphNodeWeighted;
import com.algdat.utils.GraphGeneratorWeighted;
import com.algdat.utils.GraphUtils;

public class BellmanFord {
    public static class Node implements GraphNodeWeighted<Node> {
        String id;
        HashMap<Node, Integer> edges = new HashMap<>();

        int shortestPathValue = Integer.MAX_VALUE;
        Node previous;

        public Node(String id) {
            this.id = id;
        }

		@Override
		public void addEdgeUndirected(Node node, int weight) {
            edges.put(node, weight);
            node.addEdgeDirected(this, weight);	
		}

		@Override
		public void addEdgeDirected(Node node, int weight) {
            edges.put(node, weight);
		}

		@Override
		public Map<Node, Integer> getEdges() {
            return edges;
		}

		@Override
		public boolean containsEdge(Node node) {
            return edges.containsKey(node);
		}

		@Override
		public String getId() {
            return id;
		}
    }

    public static List<Node> shortestPathBetween(List<Node> nodes, Node start, Node finish) {
        List<Node> path = new ArrayList<>();


        start.shortestPathValue = 0;

        // Relax edges
        for (int i = 0; i < nodes.size()-1; i++) {
            for (Node node : nodes) {
                for (Node edge : node.getEdges().keySet()) {
                    int potentialShortest = node.shortestPathValue + node.getEdges().get(edge);
                    if (node.shortestPathValue != Integer.MAX_VALUE && potentialShortest < edge.shortestPathValue && node.previous != edge) {
                        edge.shortestPathValue = potentialShortest;
                        edge.previous = node;
                    }
                }
            }
        }

        // Check for negative cycle
        for (Node node : nodes) {
            for (Node edge : node.getEdges().keySet()) {
                int potentialShortest = node.shortestPathValue + node.getEdges().get(edge);

                if (node.shortestPathValue != Integer.MAX_VALUE && potentialShortest < edge.shortestPathValue && node.previous != edge) {
                    System.out.println("Negative cycle");
                    return null;
                }
            }
        }

        // Find shortest path
        if (finish.previous != null) {
            path.add(finish);

            Node current = finish;

            while (current.previous != null) {
                path.add(current.previous);
                current = current.previous;
            }
        }


        return path;
    }


    public static void main(String[] args) {
        GraphGeneratorWeighted<Node> generator = new GraphGeneratorWeighted<>();
        List<Node> graph = generator.generateGraph(Node.class, 10, 0.05F, -2, 10, true, false, 1234567);

        System.out.println(GraphUtils.<Node>weightedGraphToStringDetailed(graph));

        System.out.println(GraphUtils.weightedGraphToGraphML(graph, false));

        System.out.println("Bellman Ford:");
        List<Node> path = shortestPathBetween(graph, graph.get(9), graph.get(5));

        if (path != null) {
            System.out.println(GraphUtils.<Node>nodesToStringSimpleReversed(path));
        }
    }

}
