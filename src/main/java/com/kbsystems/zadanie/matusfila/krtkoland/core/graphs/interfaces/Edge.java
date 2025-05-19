package com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.ID;

public interface Edge<V extends Vertex> extends GraphObject {

    ID getId();
    float getWeight();
    V getSource();
    V getTarget();

}
