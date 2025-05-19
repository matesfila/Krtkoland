package com.kbsystems.zadanie.matusfila.krtkoland.core.graphs;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.EntityWithID;
import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.GraphObject;

public abstract class AbstractEntityWithID implements GraphObject, EntityWithID<ID> {

    private final ID id;

    public AbstractEntityWithID(ID id) {
        this.id = id;
    }

    @Override
    public ID getId() {
        return id;
    }
}
