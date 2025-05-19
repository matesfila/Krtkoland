package com.kbsystems.zadanie.matusfila.krtkoland.core.graphs;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.Vertex;

public class AbstractVertex extends AbstractEntityWithID implements Vertex {

    public AbstractVertex(ID id) {
        super(id);
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractVertex) {
            return getId().equals(((AbstractVertex)obj).getId());
        } else {
            return false;
        }
    }
}
