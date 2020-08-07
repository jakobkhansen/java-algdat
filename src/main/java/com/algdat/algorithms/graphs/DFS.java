package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

import com.algdat.interfaces.GraphNodeUnweighted;
import com.algdat.utils.GraphGenerator;
import com.algdat.utils.GraphUtils;

public class DFS {
    public static class Node implements GraphNodeUnweighted<Node> {
        List<Node> edges = new ArrayList<>();
        boolean visited;
        String id;

        public Node(String id) {
            this.id = id;
        }

		@Override
		public boolean containsEdge(Node node) {
            return edges.contains(node);
		}

		@Override
		public List<Node> getEdges() {
            return edges;
		}

		@Override
		public String getId() {
            return id;
		}

		@Override
		public void addEdge(Node node) {
            edges.add(node);
		}
    }
    // Returns the order of nodes visited in DFS order
    public static ArrayList<Node> dfsOrder(Node start) {
        ArrayList<Node> order = new ArrayList<>();
        dfsRecursive(order, start);
        return order;
    }

    public static void dfsRecursive(ArrayList<Node> order, Node current) {
        order.add(current);
        current.visited = true;

        for (Node edge : current.getEdges()) {
            if (!edge.visited) {
                dfsRecursive(order, edge);
            }
        }
    }

    // Test
    public static void main(String[] args) {
        GraphGenerator<Node> generator = new GraphGenerator<>();
        List<Node> graph = generator.generateGraph(Node.class, 10, 0.01F, true, false, 1234567890);

        ArrayList<Node> order = dfsOrder(graph.get(0));

        System.out.println(GraphUtils.unweightedGraphToStringDetailed(graph));

        System.out.println("DFS:");
        System.out.println(GraphUtils.nodesToStringSimple(order));

        //System.out.println(GraphUtils.unweightedGraphToGraphML(graph, false));
    }


}
