package com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.enums;

import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.interfaces.KrtkolandObject;

public enum TunnelSurfaceEnu  implements KrtkolandObject {
    ASFALT(0.5f), BLATO(1), STRK(0.75f);

    public final float speedCoefficient;

    TunnelSurfaceEnu(float speedCoefficient) {
        this.speedCoefficient = speedCoefficient;
    }
}
