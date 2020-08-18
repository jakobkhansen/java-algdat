package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.algdat.interfaces.GraphNodeUnweighted;
import com.algdat.utils.GraphGenerator;
import com.algdat.utils.GraphUtils;

public class SeparationNodes {

    public static class Node implements GraphNodeUnweighted<Node> {
        int index;
        int lowNumber;
        boolean visited = false;
        int numChildren = 0;

        String id;
        List<Node> edges = new ArrayList<>();

        public Node(String id) {
            this.id = id;
        }

        @Override
        public void addEdge(Node node) {
            edges.add(node);
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

    public static ArrayList<Node> findSeparationNodes(List<Node> nodes) {
        HashSet<Node> separationNodes = new HashSet<>();

        SN_DFS(nodes.get(0), 0, separationNodes);

		return new ArrayList<Node>(separationNodes);

    }

    public static void SN_DFS(Node current, int index, HashSet<Node> separationNodes) {
        current.index = index;
        current.lowNumber = index;
        current.visited = true;
        index++;

        for (Node edge : current.edges) {
            if (!edge.visited) {
                current.numChildren++;
                SN_DFS(edge, index, separationNodes);

                // Check if childs lownumber is better
                if (edge.lowNumber < current.lowNumber) {
                    current.lowNumber = edge.lowNumber;
                }

                // Check if child has higher low number than your index, if so you're an SN
                if (current.index != 0 && current.index <= edge.lowNumber) {
                    separationNodes.add(current);
                }

            // Is backedge
            } else {
                // If backedge index is better, update lownumber
                if (edge.index < current.lowNumber) {
                    current.lowNumber = edge.index;
                }
            }
        }

        // Check if root has more than one dfs child
        if (current.index == 0 && current.numChildren > 1) {
            separationNodes.add(current);
        }
    }

    public static void main(String[] args) {
        GraphGenerator<Node> graphGenerator = new GraphGenerator<>();
        List<Node> graph = graphGenerator.generateGraph(Node.class, 10, 0.05F, true, false, 1234567891);

        System.out.println(GraphUtils.unweightedGraphToStringDetailed(graph));
        System.out.println(GraphUtils.unweightedGraphToGraphML(graph, false));

        List<Node> separationNodes = findSeparationNodes(graph);

        System.out.println(GraphUtils.nodesToStringSimple(separationNodes));
    }
}
