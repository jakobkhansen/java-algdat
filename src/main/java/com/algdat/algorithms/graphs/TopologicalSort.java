package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

import com.algdat.interfaces.GraphNodeUnweighted;
import com.algdat.utils.GraphGenerator;
import com.algdat.utils.GraphUtils;

public class TopologicalSort {
    public static class Node implements GraphNodeUnweighted<Node> {

        int inDeg = 0;
        List<Node> edges = new ArrayList<>();
        String id;

        public Node(String id) {
            this.id = id;
        }

		@Override
		public void addEdge(Node node) {
            edges.add(node);
            node.inDeg++;	
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

    }

    public static List<Node> topologicalSort(List<Node> nodes) {

        List<Node> topSortList = new ArrayList<>();
        List<Node> inDegZero = new ArrayList<>();

        for (Node node : nodes) {
            if (node.inDeg == 0) {
                inDegZero.add(node);
            }
        }

        while (inDegZero.size() > 0) {
            Node current = inDegZero.remove(inDegZero.size() - 1);
            topSortList.add(current);

            for (Node edge : current.edges) {
                edge.inDeg--;

                if (edge.inDeg == 0) {
                    inDegZero.add(edge);
                }
            }
        }

        // Graph contains cycle
        if (topSortList.size() < nodes.size()) {
            return null;
        }

        return topSortList;
    }

    public static void main(String[] args) {
        GraphGenerator<Node> generator = new GraphGenerator<>();

        List<Node> graph = generator.generateGraph(Node.class, 10, 0.05F, true, true, 1234567892);

        System.out.println(GraphUtils.unweightedGraphToStringDetailed(graph));

        //System.out.println(GraphUtils.unweightedGraphToGraphML(graph, true));

        List<Node> topSorted = topologicalSort(graph);

        if (topSorted != null) {
            System.out.println(GraphUtils.nodesToStringSimple(topSorted));
        } else {
            System.out.println("Cycle in graph...");
        }



    }
}
