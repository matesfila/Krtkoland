package com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.ID;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.interfaces.KrtkolandObject;

public class Tower extends Room implements KrtkolandObject {

    /**
     * Počet schodov, ktoré treba prekonať pri vstupe/výstup do/z veže.
     */
    private final int stairsCount;

    public Tower(String id, int stairsCount) {
        super(new ID(id));
        this.stairsCount = stairsCount;
    }

    public int getStairsCount() {
        return stairsCount;
    }

    /**
     * Váhou veže bude počet schodíkov krát 0.5,
     * pretože jeden schodík vie krtko prejsť za 0.5 sekundy.
     */
    @Override
    public float getWeight() {
        return stairsCount * 0.5f;
    }
}
