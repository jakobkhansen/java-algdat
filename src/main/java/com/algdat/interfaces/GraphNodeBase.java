package com.algdat.interfaces;

public interface GraphNodeBase<T extends GraphNodeBase<T>> {
    public boolean containsEdge(T node);
    public String getId();
}
