package com.algdat.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

import com.algdat.datastructures.graphs.Node;
import com.algdat.utils.GraphUtils;

public class BellmanFord {
    public static List<Node> shortestPathBetween(Node start, Node finish) {
        List<Node> path = new ArrayList<>();



        return path;
    }


    public static void main(String[] args) {
        List<Node> graph = GraphUtils.generateGraph(10, 0.2F, -10, 10, true, false, 123456789);

        System.out.println(GraphUtils.nodesToStringDetailed(graph));
    }

}
