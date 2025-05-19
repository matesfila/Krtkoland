package com.kbsystems.zadanie.matusfila.krtkoland.core.graphs;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.Vertex;

public class AbstractVertex extends AbstractEntityWithID implements Vertex {

    public AbstractVertex(ID id) {
        super(id);
    }
}
