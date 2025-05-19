package com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.impl.OrientedGraph;

import java.util.UUID;

public class KrtkolandImpl extends OrientedGraph<Room, Tunnel> {

    /**
     * Preťaženie addEdge - krtkoland je totiž symetrický graf, takže pri pridaní hrany a,b
     * sa automaticky pridá aj hrana b,a.
     */
    @Override
    public void addEdge(Tunnel edge) {
        super.addEdge(edge);
        super.addEdge(new Tunnel(
                UUID.randomUUID().toString(),
                edge.getTarget(),
                edge.getSource(),
                edge.getLengthMeters(),
                edge.getSurface(),
                edge.isLightningIsOk()
        ));
    }

}
