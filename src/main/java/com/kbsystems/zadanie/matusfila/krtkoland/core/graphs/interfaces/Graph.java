package com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces;

import java.util.Set;

public interface Graph<V extends Vertex, E extends Edge<V>> extends GraphObject {

    Set<E> getEdges();
    Set<V> getVertices();

    void addEdge(E edge);
    boolean isEdge(V source, V target);
    Set<E> allEdges(V vertex);

}
