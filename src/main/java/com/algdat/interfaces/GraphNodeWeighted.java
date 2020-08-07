package com.algdat.interfaces;


import java.util.Map;

public interface GraphNodeWeighted<T extends GraphNodeWeighted<T>> extends GraphNodeBase<T> { 
    public Map<T, Integer> getEdges();
}
