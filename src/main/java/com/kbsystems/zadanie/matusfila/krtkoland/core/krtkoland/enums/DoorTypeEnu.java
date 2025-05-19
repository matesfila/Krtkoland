package com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.enums;

public enum DoorTypeEnu {

    RED(5),
    BLUE(3),
    GREEN(2);

    public final float openingTimeSeconds;

    DoorTypeEnu(float openingTimeSeconds) {
        this.openingTimeSeconds = openingTimeSeconds;
    }
}
