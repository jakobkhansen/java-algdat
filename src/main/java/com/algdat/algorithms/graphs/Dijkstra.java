package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algdat.datastructures.containers.PriorityQueue;
import com.algdat.interfaces.GraphNodeWeighted;
import com.algdat.utils.GraphGeneratorWeighted;
import com.algdat.utils.GraphUtils;

public class Dijkstra {
    public static class Node implements GraphNodeWeighted<Node> {
        String id;
        HashMap<Node, Integer> edges = new HashMap<>();

        int shortestPathValue = Integer.MAX_VALUE;
        boolean finished = false;
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

    public static List<Node> dijkstraShortestPath(Node start, Node finish) {
        List<Node> path = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(false);

        pq.addElement(start, 0);
        start.shortestPathValue = 0;

        while (!pq.isEmpty()) {

            // Visualize priority queue updates
            //System.out.println(pq);

            Node current = pq.pop();

            if (current == finish) {
                break;
            }

            for (Node edge : current.getEdges().keySet()) {
                if (!edge.finished) {
                    int potentialRelax = current.shortestPathValue + current.getEdges().get(edge);

                    if (potentialRelax < edge.shortestPathValue) {
                        edge.shortestPathValue = potentialRelax;
                        edge.previous = current;

                        pq.updatePriority(edge, edge.shortestPathValue);
                        if (pq.contains(edge)) {
                            pq.updatePriority(edge, edge.shortestPathValue);
                        } else {
                            pq.addElement(edge, edge.shortestPathValue);
                        }
                    }
                }
            }
            current.finished = true;
        }

        if (finish.previous != null) {
            path.add(finish);
            Node pathNode = finish;
            while (pathNode.previous != null) {
                path.add(pathNode.previous);
                pathNode = pathNode.previous;
            }
        }



        return path;
    }

    // Test
    public static void main(String[] args) {
        GraphGeneratorWeighted<Node> generator = new GraphGeneratorWeighted<>();
        List<Node> graph = generator.generateGraph(Node.class, 10, 0.01F, 1, 20, true, false, 123456789);

        System.out.println(GraphUtils.weightedGraphToStringDetailed(graph));

        List<Node> path = Dijkstra.dijkstraShortestPath(graph.get(6), graph.get(9));

        System.out.println(GraphUtils.nodesToStringSimpleReversed(path));

        System.out.println(GraphUtils.weightedGraphToGraphML(graph, false));
    }
}
