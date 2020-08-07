package com.algdat.interfaces;


import java.util.Map;

public interface GraphNodeWeighted<T extends GraphNodeWeighted<T>> extends GraphNodeBase<T> { 
    public void addEdgeUndirected(T node, int weight);
    public void addEdgeDirected(T node, int weight);
    public Map<T, Integer> getEdges();
}
