package com.algdat.interfaces;


import java.util.List;

public interface GraphNodeUnweighted<T extends GraphNodeUnweighted<T>> extends GraphNodeBase<T> {
    public void addEdgeUndirected(T node);
    public void addEdgeDirected(T node);
    public List<T> getEdges();
}
