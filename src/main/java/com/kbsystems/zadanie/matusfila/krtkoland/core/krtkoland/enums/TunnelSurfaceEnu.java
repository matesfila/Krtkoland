package com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.enums;

public enum TunnelSurfaceEnu {
    ASFALT(0.5f), BLATO(1), STRK(0.75f);

    public final float speedCoefficient;

    TunnelSurfaceEnu(float speedCoefficient) {
        this.speedCoefficient = speedCoefficient;
    }
}
