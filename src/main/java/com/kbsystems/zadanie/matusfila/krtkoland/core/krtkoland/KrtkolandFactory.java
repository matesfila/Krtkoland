package com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland;

import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.enums.TunnelSurfaceEnu;

import java.util.UUID;

public class KrtkolandFactory {


    public static void addSimplePath(KrtkolandImpl k, Room... rooms) {
        for (int i = 0; i < rooms.length - 1; i++) {
            Room r1 = rooms[i];
            Room r2 = rooms[i+1];
            if (!k.isEdge(r1, r2)) {
                k.addEdge(
                        new Tunnel(
                                UUID.randomUUID().toString(),
                                r1,
                                r2,
                                1,
                                TunnelSurfaceEnu.BLATO,
                                true
                        )
                );
            }
        }
    }

}
