package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

import com.algdat.interfaces.GraphNodeUnweighted;
import com.algdat.utils.GraphGeneratorUnweighted;
import com.algdat.utils.GraphUtils;

public class BFS {
    public static class Node implements GraphNodeUnweighted<Node> {
        boolean visited = false;
        List<Node> edges = new ArrayList<>();
        String id;

        public Node(String id) {
            this.id = id;
        }

        @Override
		public void addEdgeUndirected(Node node) {
            edges.add(node);	
            node.addEdgeDirected(this);
		}

		@Override
		public void addEdgeDirected(Node node) {
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
    public static List<List<Node>> bfs(Node start) {
        List<List<Node>> layers = new ArrayList<>();

        layers.add(new ArrayList<>());

        layers.get(0).add(start);
        start.visited = true;

        for (int i = 0; layers.get(i).size() > 0; i++) {
            layers.add(new ArrayList<>());

            for (Node node : layers.get(i)) {
                for (Node edge : node.edges) {
                    if (!edge.visited) {
                        edge.visited = true;
                        layers.get(i+1).add(edge);
                    }
                }
            }
        }

        return layers.subList(0, layers.size() - 1);
    }

    // Test
    public static void main(String[] args) {
        GraphGeneratorUnweighted<Node> generator = new GraphGeneratorUnweighted<>();
        List<Node> nodes = generator.generateGraph(Node.class, 5, 0.2F, true, false, 123456789);

        System.out.println(GraphUtils.unweightedGraphToStringDetailed(nodes));

        System.out.println(GraphUtils.unweightedGraphToGraphML(nodes, false));

        List<List<Node>> bfsLayers = bfs(nodes.get(0));

        System.out.println("BFS: \n" + layersToString(bfsLayers));;
    }

    // toString
    public static String layersToString(List<List<Node>> layers) {
        String ret = "";

        for (int i = 0; i < layers.size(); i++) {
            ret += "Layer " + i + " [ ";

            for (Node node : layers.get(i)) {
                ret += node.getId() + " ";
            }
            ret += "]\n";
        }
        return ret;
    }
}
