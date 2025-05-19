package com.kbsystems.zadanie.matusfila.krtkoland.core.graphs;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.Edge;
import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.Graph;
import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.Vertex;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AbstractGraph<V extends Vertex, E extends Edge<V>> implements Graph<V, E> {

    private Set<E> edges;
    private Set<V> vertices;

    public AbstractGraph() {
        //this.orientation = orientation;
        edges = new HashSet<>();
        vertices = new HashSet<>();
    }

//    public static enum OrientationEnu {
//        ORIENTED, SYMETRIC
//    }

    @Override
    public Set<E> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    @Override
    public Set<V> getVertices() {
        return Collections.unmodifiableSet(vertices);
    }

    @Override
    public void addEdge(E edge) {
        vertices.add(edge.getSource());
        vertices.add(edge.getTarget());
        edges.add(edge);
    }

    @Override
    public boolean isEdge(V source, V target) {
        return edges.stream().anyMatch(e -> Objects.equals(e.getSource(), source) && Objects.equals(e.getTarget(), target));
    }

    @Override
    public Set<E> allEdges(V source) {
        return edges.stream().filter(e -> Objects.equals(e.getSource(), source)).collect(Collectors.toUnmodifiableSet());
    }

}
