package com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.AbstractEdge;
import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.ID;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.enums.TunnelSurfaceEnu;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.interfaces.KrtkolandObject;

public class Tunnel extends AbstractEdge<Room> implements KrtkolandObject {

    /**
     * Dĺžka v metroch.
     */
    private final float lengthMeters;

    /**
     * Typ povrchu - mení počet sekúnd potrebných na prejdenie tunela.
     */
    private final TunnelSurfaceEnu surface;

    /**
     * Či funguje osvetlenie.
     */
    private final boolean lightningIsOk;

    public Tunnel(String id, Room source, Room target, float lengthMeters, TunnelSurfaceEnu surface, boolean lightningIsOk) {
        super(new ID(id), source, target);
        this.lengthMeters = lengthMeters;
        this.surface = surface;
        this.lightningIsOk = lightningIsOk;
    }

    /**
     * Váhou tunelu sú sekundy - čas potrebný na prejdenie tunela.
     */
    @Override
    public float getWeight() {
        float weight = lengthMeters * surface.speedCoefficient;
        if (!lightningIsOk) {
            weight = weight * 1.2f;
        }

        return weight + getSource().getWeight() + getTarget().getWeight();
    }
}
