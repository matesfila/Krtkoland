package com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.impl.OrientedGraph;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

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

    public float computePathWeight(List<Room> rooms) {
        return (float) IntStream.range(0, rooms.size() - 1)
                //vytvoríme si dvojice susedných prvkov
                .mapToObj(i -> Arrays.asList(rooms.get(i), rooms.get(i+1)))
                //pre každú dvojicu najdeme hranu
                .map(pair -> findEdge(pair.get(0), pair.get(1)))
                //spočítame váhy všetkých hrán
                .mapToDouble(Tunnel::getWeight).sum();
    }

}
