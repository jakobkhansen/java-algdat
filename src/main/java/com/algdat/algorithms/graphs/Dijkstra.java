package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

import com.algdat.datastructures.containers.PriorityQueue;
import com.algdat.datastructures.graphs.Node;
import com.algdat.utils.GraphUtils;

public class Dijkstra {

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

            for (Node edge : current.edges.keySet()) {
                if (!edge.finished) {
                    int potentialRelax = current.shortestPathValue + current.edges.get(edge);

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
        List<Node> graph = GraphUtils.generateGraph(10, 0.1F, 20, true, false, 123456789);

        System.out.println(GraphUtils.nodesToStringDetailed(graph));

        List<Node> path = Dijkstra.dijkstraShortestPath(graph.get(6), graph.get(9));

        System.out.println(GraphUtils.nodesToStringSimpleReversed(path));

        //System.out.println(GraphUtils.graphToGraphML(graph, false));
    }
}
