package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import com.algdat.interfaces.GraphNodeUnweighted;
import com.algdat.utils.GraphGenerator;
import com.algdat.utils.GraphUtils;

public class KosarajuComponents {

    public static class Node implements GraphNodeUnweighted<Node> {

        LinkedList<Node> edges = new LinkedList<>();
        String id;
        int numEdges = 0;
        boolean visited;
        int index;

        public Node(String id) {
            this.id = id;
        }

		@Override
		public void addEdge(Node node) {
            edges.add(node);
            numEdges++;
		}

		@Override
		public boolean containsEdge(Node node) {
            return edges.contains(node);
		}

		@Override
		public String getId() {
            return id;
		}

		@Override
		public List<Node> getEdges() {
            return edges;
		}

        public void addEdgeReversed(Node edge) {
            edges.add(edge);
        }
    }

    public static List<List<Node>> stronglyConnectedComponents(List<Node> graph) {


        LinkedList<Node> order = new LinkedList<>();

        for (Node node : graph) {
            if (!node.visited) {
                dfsOrder(node, order);
            }
        }


        reverseGraph(graph);

        List<List<Node>> components = new ArrayList<>();

        for (Node node : order) {
            node.visited = false;
        }

        for (Node node : order) {
            if (!node.visited) {
                List<Node> component = new ArrayList<>();
                dfsComponents(node, component);
                components.add(component);
            }
        }

        reverseGraph(graph);

        return components;
    }

    public static void dfsOrder(Node current, LinkedList<Node> order) {
        current.visited = true;

        for (Node edge : current.edges) {
            if (!edge.visited) {
                dfsOrder(edge, order);
            }
        }

        order.addFirst(current);
    }

    public static void reverseGraph(List<Node> graph) {
        for (Node node : graph) {
            for (int i = 0; i < node.numEdges; i++) {
                Node edge = node.edges.pop();
                edge.addEdgeReversed(node);
            }
            node.numEdges = node.edges.size();
        }
    }

    public static List<Node> dfsComponents(Node current, List<Node> components) {
        current.visited = true;
        components.add(current);

        for (Node edge : current.edges) {
            if (!edge.visited) {
                dfsComponents(edge, components);
            }
        }

        return components;
    }


    public static void main(String[] args) {
        GraphGenerator<Node> generator = new GraphGenerator<>();
        List<Node> graph = generator.generateGraph(Node.class, 10, 0.2F, false, true, 1234567890);

        //System.out.println(GraphUtils.unweightedGraphToGraphML(graph, true));

        List<List<Node>> components = stronglyConnectedComponents(graph);

        for (List<Node> component : components) {
            System.out.print("Component: ");
            for (Node node : component) {
                System.out.print(node.id + " ");
            }
            System.out.println("");
        }
    }
}
