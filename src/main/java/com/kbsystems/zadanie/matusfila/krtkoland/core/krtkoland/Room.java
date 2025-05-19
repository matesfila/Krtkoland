package com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.AbstractVertex;
import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.ID;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.interfaces.KrtkolandObject;

/**
 * Room reprezentuje "miestnosť", tj. bunker, sklad alebo vežu. Charakterizáciou
 * miestnosti je jej váha, ktorá sa pridá k váhe tunelu vedúceho do danej miestnosti.
 * Napríklad pri tuneli bude váhu ovlyvňovať dĺžka rebríka, pri bunkri farba dverí.
 */
public abstract class Room extends AbstractVertex implements KrtkolandObject {

    public Room(ID id) {
        super(id);
    }

    public abstract float getWeight();

}
