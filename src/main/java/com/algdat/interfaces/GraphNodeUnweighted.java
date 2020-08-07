package com.algdat.interfaces;


import java.util.List;

public interface GraphNodeUnweighted<T extends GraphNodeUnweighted<T>> extends GraphNodeBase<T> {
    public List<T> getEdges();
}
