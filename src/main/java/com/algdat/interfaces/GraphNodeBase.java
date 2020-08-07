package com.algdat.interfaces;

public interface GraphNodeBase<T extends GraphNodeBase<T>> {
    public void addEdge(T node);
    default public void addEdge(T node, int weight) {
        addEdge(node);
    };
    public boolean containsEdge(T node);
    public String getId();
}
