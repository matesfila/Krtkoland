package com.kbsystems.zadanie.matusfila.krtkoland.core.graphs;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.Edge;
import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.Vertex;

public abstract class AbstractEdge<V extends Vertex> extends AbstractEntityWithID implements Edge<V>, Comparable<Edge<V>> {

    private final V source;
    private final V target;

    public AbstractEdge(ID id, V source, V target) {
        super(id);
        this.source = source;
        this.target = target;
    }

    @Override
    public float getWeight() {
        return 1;
    }

    @Override
    public V getSource() {
        return source;
    }

    @Override
    public V getTarget() {
        return target;
    }

    @Override
    public int compareTo(Edge<V> o) {
        return Float.compare(getWeight(), o.getWeight());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

}
