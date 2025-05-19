package com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.ID;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.enums.DoorTypeEnu;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.interfaces.KrtkolandObject;

public class Bunker extends Room implements KrtkolandObject {

    private final DoorTypeEnu doorType;

    public Bunker(String id, DoorTypeEnu doorType) {
        super(new ID(id));
        this.doorType = doorType;
    }

    public DoorTypeEnu getDoorType() {
        return doorType;
    }

    @Override
    public float getWeight() {
        return doorType.openingTimeSeconds;
    }
}
