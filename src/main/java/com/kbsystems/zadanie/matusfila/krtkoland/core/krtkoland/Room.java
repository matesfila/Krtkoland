package com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.AbstractVertex;
import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.ID;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.interfaces.KrtkolandObject;

public abstract class Room extends AbstractVertex implements KrtkolandObject {

    public Room(ID id) {
        super(id);
    }

    public abstract float getWeight();

}
