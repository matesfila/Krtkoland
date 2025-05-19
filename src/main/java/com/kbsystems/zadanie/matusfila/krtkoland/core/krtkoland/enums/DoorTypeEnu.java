package com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.enums;

import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.interfaces.KrtkolandObject;

public enum DoorTypeEnu implements KrtkolandObject {

    RED(5),
    BLUE(3),
    GREEN(2);

    public final float openingTimeSeconds;

    DoorTypeEnu(float openingTimeSeconds) {
        this.openingTimeSeconds = openingTimeSeconds;
    }
}
