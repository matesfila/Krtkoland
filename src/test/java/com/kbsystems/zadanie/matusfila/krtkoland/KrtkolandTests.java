package com.kbsystems.zadanie.matusfila.krtkoland;

import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.Bunker;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.Tower;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.Tunnel;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.Warehouse;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.enums.DoorTypeEnu;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.enums.TunnelSurfaceEnu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KrtkolandTests {

	@Test
	public void testTunnelWeights() {

		Tower t1 = new Tower("t1", 1);
		Tower t2 = new Tower("t2", 2);
		Tower t3 = new Tower("t3", 3);
		Tower t4 = new Tower("t4", 4);
		Tower t5 = new Tower("t5", 5);
		Bunker b1 = new Bunker("b1", DoorTypeEnu.BLUE);
		Bunker b2 = new Bunker("b2", DoorTypeEnu.GREEN);
		Bunker b3 = new Bunker("b3", DoorTypeEnu.RED);
		Bunker b4 = new Bunker("b4", DoorTypeEnu.GREEN);
		Bunker b5 = new Bunker("b5", DoorTypeEnu.RED);
		Warehouse w1 = new Warehouse("sklad1", DoorTypeEnu.BLUE);
		Warehouse w2 = new Warehouse("sklad2", DoorTypeEnu.GREEN);
		Warehouse w3 = new Warehouse("sklad3", DoorTypeEnu.RED);

		{
			Tunnel tunel1 = new Tunnel("tunel_1", t1, b1, 4, TunnelSurfaceEnu.BLATO, true);
			Tunnel tunel2 = new Tunnel("tunel_2", t1, b1, 5, TunnelSurfaceEnu.ASFALT, true);
			Tunnel tunel3 = new Tunnel("tunel_3", t1, b1, 6, TunnelSurfaceEnu.STRK, true);

			//tunel + schody veze + dvere bunkra
			Assertions.assertEquals(0, Float.compare(tunel1.getWeight(), 4 * 1.0f * 1 + 0.5f + 3f));
			Assertions.assertEquals(0, Float.compare(tunel2.getWeight(), 5 * 0.5f * 1 + 0.5f + 3f));
			Assertions.assertEquals(0, Float.compare(tunel3.getWeight(), 6 * 0.75f * 1 + 0.5f + 3f));
		}

		{
			Tunnel tunel1 = new Tunnel("tunel_1", t1, b1, 44, TunnelSurfaceEnu.BLATO, false);
			Tunnel tunel2 = new Tunnel("tunel_2", t1, b1, 55, TunnelSurfaceEnu.ASFALT, false);
			Tunnel tunel3 = new Tunnel("tunel_3", t1, b1, 66, TunnelSurfaceEnu.STRK, false);

			Assertions.assertEquals(0, Float.compare(tunel1.getWeight(), 44 * 1.0f * 1.2f + 0.5f + 3f));
			Assertions.assertEquals(0, Float.compare(tunel2.getWeight(), 55 * 0.5f * 1.2f + 0.5f + 3f));
			Assertions.assertEquals(0, Float.compare(tunel3.getWeight(), 66 * 0.75f * 1.2f + 0.5f + 3f));
		}

		{
			Tunnel tunel1 = new Tunnel("tunel_1", t1, b1, 44, TunnelSurfaceEnu.BLATO, false);
			Tunnel tunel2 = new Tunnel("tunel_2", t1, b2, 55, TunnelSurfaceEnu.ASFALT, false);
			Tunnel tunel3 = new Tunnel("tunel_3", t1, b3, 66, TunnelSurfaceEnu.STRK, false);

			Assertions.assertEquals(0, Float.compare(tunel1.getWeight(), 44 * 1.0f * 1.2f + 0.5f + 3f));
			Assertions.assertEquals(0, Float.compare(tunel2.getWeight(), 55 * 0.5f * 1.2f + 0.5f + 2f));
			Assertions.assertEquals(0, Float.compare(tunel3.getWeight(), 66 * 0.75f * 1.2f + 0.5f + 5f));
		}
		{
			Tunnel tunel1 = new Tunnel("tunel_1", t1, w1, 44, TunnelSurfaceEnu.BLATO, false);
			Tunnel tunel2 = new Tunnel("tunel_2", t1, w2, 55, TunnelSurfaceEnu.ASFALT, false);
			Tunnel tunel3 = new Tunnel("tunel_3", t1, w3, 66, TunnelSurfaceEnu.STRK, false);

			Assertions.assertEquals(0, Float.compare(tunel1.getWeight(), 44 * 1.0f * 1.2f + 0.5f + 3f));
			Assertions.assertEquals(0, Float.compare(tunel2.getWeight(), 55 * 0.5f * 1.2f + 0.5f + 2f));
			Assertions.assertEquals(0, Float.compare(tunel3.getWeight(), 66 * 0.75f * 1.2f + 0.5f + 5f));
		}

	}

	@Test
	public void testFindBestPath() {
		// TODO
//		LandPath landPath = krtkoService.theBestTimeAndPath(new Tower());
//		Assertions.assertNotNull(landPath);
	}


}
