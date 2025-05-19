package com.kbsystems.zadanie.matusfila.krtkoland.models;

import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.*;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.enums.DoorTypeEnu;
import org.springframework.stereotype.Component;

@Component
public class KrtkolandMock extends KrtkolandImpl {

    public static Tower[] towers;
    public static Bunker[] bunkers;
    public static Warehouse[] warehouses;

    static {
        towers = new Tower[]{
                new Tower("t0", 1),
                new Tower("t1", 2),
                new Tower("t2", 3),
                new Tower("t3", 4),
                new Tower("t4", 5),
                new Tower("t5", 6),
                new Tower("t6", 7)
        };
        bunkers = new Bunker[]{
                new Bunker("b0", DoorTypeEnu.GREEN),
                new Bunker("b1", DoorTypeEnu.BLUE),
                new Bunker("b2", DoorTypeEnu.RED),
                new Bunker("b3", DoorTypeEnu.GREEN),
                new Bunker("b4", DoorTypeEnu.GREEN),
                new Bunker("b5", DoorTypeEnu.GREEN),
                new Bunker("b6", DoorTypeEnu.RED)
        };
        warehouses = new Warehouse[]{
                new Warehouse("sklad", DoorTypeEnu.GREEN),
        };
    }

    public KrtkolandMock() {
        KrtkolandFactory.addSimplePath(this, towers[0], bunkers[1], warehouses[0]);
        KrtkolandFactory.addSimplePath(this, towers[0], bunkers[1], bunkers[2], warehouses[0]);
        KrtkolandFactory.addSimplePath(this, towers[3], bunkers[2], bunkers[5], bunkers[6], warehouses[0]);
    }
}
