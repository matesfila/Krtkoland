package com.kbsystems.zadanie.matusfila.krtkoland;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.algorithms.DijkstraGraphAlgorithm;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.*;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.enums.DoorTypeEnu;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.enums.TunnelSurfaceEnu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KrtkolandTests {

	private static Tower[] towers;
	private static Bunker[] bunkers;
	private static Warehouse[] warehouses;

	@BeforeAll
	public static void initialize() {

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
				new Warehouse("sklad0", DoorTypeEnu.GREEN),
				new Warehouse("sklad1", DoorTypeEnu.BLUE),
				new Warehouse("sklad2", DoorTypeEnu.RED)
		};
	}

	private static void updateToFullGraph(KrtkolandImpl k, int lengthMeters) {
		int i = 0;
		for (Room r1 : k.getVertices()) {
			for (Room r2 : k.getVertices()) {
				if (!k.isEdge(r1, r2) && !Objects.equals(r1, r2)) {
					k.addEdge(new Tunnel(String.valueOf(i++), r1, r2, lengthMeters, TunnelSurfaceEnu.BLATO, true));
				}
			}
		}
	}




	@Test
	public void testGraphBasics() {
		KrtkolandImpl krtkoland = new KrtkolandImpl();
		KrtkolandFactory.addSimplePath(krtkoland, towers[0], bunkers[1], warehouses[0]);
		KrtkolandFactory.addSimplePath(krtkoland, towers[0], bunkers[1], bunkers[2], warehouses[0]);
		KrtkolandFactory.addSimplePath(krtkoland, towers[3], bunkers[2], bunkers[5], bunkers[6], warehouses[0]);
		//printAllEdges(krtkoland);

		assertEquals(1, krtkoland.allEdges(towers[0]).size());
		Assertions.assertTrue(krtkoland.allNeighbors(towers[0]).containsAll(Arrays.asList(bunkers[1])));

		assertEquals(3, krtkoland.allEdges(bunkers[1]).size());
		Assertions.assertTrue(krtkoland.allNeighbors(bunkers[1]).containsAll(Arrays.asList(towers[0], bunkers[2], warehouses[0])));

		assertEquals(3, krtkoland.allEdges(warehouses[0]).size());
		Assertions.assertTrue(krtkoland.allNeighbors(warehouses[0]).containsAll(Arrays.asList(bunkers[1], bunkers[2], bunkers[6])));

		assertEquals(2, krtkoland.allEdges(bunkers[5]).size());
		Assertions.assertTrue(krtkoland.allNeighbors(bunkers[5]).containsAll(Arrays.asList(bunkers[2], bunkers[6])));

	}

	/**
	 * Test metódy na výpočet váhy celej cesty krtkoland.computePathWeight.
	 */
	@Test
	public void testPathWeights() {
		Tunnel tunel1 = new Tunnel("tunel_1", towers[0], bunkers[0], 4, TunnelSurfaceEnu.BLATO, true);
		Tunnel tunel2 = new Tunnel("tunel_2", bunkers[0], bunkers[1], 5, TunnelSurfaceEnu.BLATO, true);
		Tunnel tunel3 = new Tunnel("tunel_3", bunkers[1], warehouses[0], 6, TunnelSurfaceEnu.BLATO, true);

		//tunel + schody veze + dvere bunkra
		float tunel1Weight = (4 * 1.0f * 1) + 0.5f + 2f;
		float tunel2Weight = 2f + 5 + 3;
		float tunel3Weight = 3f + 6 + 2f;

		assertEquals(0, Float.compare(tunel1.getWeight(), tunel1Weight));
		assertEquals(0, Float.compare(tunel2.getWeight(), tunel2Weight));
		assertEquals(0, Float.compare(tunel3.getWeight(), tunel3Weight));

		KrtkolandImpl krtkoland = new KrtkolandImpl();
		krtkoland.addEdge(tunel1);
		krtkoland.addEdge(tunel2);
		krtkoland.addEdge(tunel3);

		float pathWeight = krtkoland.computePathWeight(Arrays.asList(towers[0], bunkers[0], bunkers[1], warehouses[0]));

		assertEquals(0, Float.compare(pathWeight, tunel1Weight + tunel2Weight + tunel3Weight));
	}

	/**
	 * Test na korektnosť počítania váhy tunelov (čo je suma váh veže, tunelu a dverí).
	 */
	@Test
	public void testTunnelWeights() {

		{
			Tunnel tunel1 = new Tunnel("tunel_1", towers[0], bunkers[0], 4, TunnelSurfaceEnu.BLATO, true);
			Tunnel tunel2 = new Tunnel("tunel_2", towers[0], bunkers[0], 5, TunnelSurfaceEnu.ASFALT, true);
			Tunnel tunel3 = new Tunnel("tunel_3", towers[0], bunkers[0], 6, TunnelSurfaceEnu.STRK, true);

			//tunel + schody veze + dvere bunkra
			assertEquals(0, Float.compare(tunel1.getWeight(), (4 * 1.0f * 1) + 0.5f + 2f));
			assertEquals(0, Float.compare(tunel2.getWeight(), (5 * 0.5f * 1) + 0.5f + 2f));
			assertEquals(0, Float.compare(tunel3.getWeight(), (6 * 0.75f * 1) + 0.5f + 2f));
		}

		{
			Tunnel tunel1 = new Tunnel("tunel_1", towers[0], bunkers[0], 44, TunnelSurfaceEnu.BLATO, false);
			Tunnel tunel2 = new Tunnel("tunel_2", towers[0], bunkers[0], 55, TunnelSurfaceEnu.ASFALT, false);
			Tunnel tunel3 = new Tunnel("tunel_3", towers[0], bunkers[0], 66, TunnelSurfaceEnu.STRK, false);

			assertEquals(0, Float.compare(tunel1.getWeight(), 44 * 1.0f * 1.2f + 0.5f + 2f));
			assertEquals(0, Float.compare(tunel2.getWeight(), 55 * 0.5f * 1.2f + 0.5f + 2f));
			assertEquals(0, Float.compare(tunel3.getWeight(), 66 * 0.75f * 1.2f + 0.5f + 2f));
		}

		{
			Tunnel tunel1 = new Tunnel("tunel_1", towers[0], bunkers[0], 44, TunnelSurfaceEnu.BLATO, false);
			Tunnel tunel2 = new Tunnel("tunel_2", towers[0], bunkers[1], 55, TunnelSurfaceEnu.ASFALT, false);
			Tunnel tunel3 = new Tunnel("tunel_3", towers[0], bunkers[2], 66, TunnelSurfaceEnu.STRK, false);

			assertEquals(0, Float.compare(tunel1.getWeight(), 44 * 1.0f * 1.2f + 0.5f + 2f));
			assertEquals(0, Float.compare(tunel2.getWeight(), 55 * 0.5f * 1.2f + 0.5f + 3f));
			assertEquals(0, Float.compare(tunel3.getWeight(), 66 * 0.75f * 1.2f + 0.5f + 5f));
		}
		{
			Tunnel tunel1 = new Tunnel("tunel_1", towers[0], warehouses[0], 44, TunnelSurfaceEnu.BLATO, false);
			Tunnel tunel2 = new Tunnel("tunel_2", towers[0], warehouses[1], 55, TunnelSurfaceEnu.ASFALT, false);
			Tunnel tunel3 = new Tunnel("tunel_3", towers[0], warehouses[2], 66, TunnelSurfaceEnu.STRK, false);

			assertEquals(0, Float.compare(tunel1.getWeight(), 44 * 1.0f * 1.2f + 0.5f + 2f));
			assertEquals(0, Float.compare(tunel2.getWeight(), 55 * 0.5f * 1.2f + 0.5f + 3f));
			assertEquals(0, Float.compare(tunel3.getWeight(), 66 * 0.75f * 1.2f + 0.5f + 5f));
		}

	}

	@Test
	public void testFindBestPath() {
		{
			KrtkolandImpl krtkoland = new KrtkolandImpl();
			KrtkolandFactory.addSimplePath(krtkoland, towers[0], bunkers[1], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[0], bunkers[1], bunkers[2], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[3], bunkers[2], bunkers[5], bunkers[6], warehouses[0]);
			//printAllEdges(krtkoland);

			// do grafu sa doplnia automaticky tunely medzi všetkými vrcholmi, ich hodnota je schválne veľká, aby najkratšou cestou
			// bola cesta ktorú zadáme manuálne
			updateToFullGraph(krtkoland, 1000);

			List<Room> shortestPath = DijkstraGraphAlgorithm.findShortestPath(krtkoland, towers[0], warehouses[0]).get();
			Utils.printPath(shortestPath);
			Assertions.assertIterableEquals(
					Arrays.asList(towers[0], bunkers[1], warehouses[0]),
					shortestPath
			);

		}
		{
			KrtkolandImpl krtkoland = new KrtkolandImpl();
			KrtkolandFactory.addSimplePath(krtkoland, towers[0], bunkers[2], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[0], bunkers[1], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[3], bunkers[1], bunkers[2], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[3], bunkers[2], bunkers[5], bunkers[6], warehouses[0]);
			//printAllEdges(krtkoland);

			updateToFullGraph(krtkoland, 1000);

			List<Room> shortestPath = DijkstraGraphAlgorithm.findShortestPath(krtkoland, towers[0], warehouses[0]).get();
			Utils.printPath(shortestPath);
			Assertions.assertIterableEquals(
					Arrays.asList(towers[0], bunkers[1], warehouses[0]),
					shortestPath
			);

		}
		{
			KrtkolandImpl krtkoland = new KrtkolandImpl();
			KrtkolandFactory.addSimplePath(krtkoland, towers[0], bunkers[2], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[0], bunkers[1], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[0], bunkers[1], bunkers[2], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[3], bunkers[2], bunkers[5], bunkers[6], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[0], bunkers[0], warehouses[0]);
			//printAllEdges(krtkoland);

			updateToFullGraph(krtkoland, 1000);

			List<Room> shortestPath = DijkstraGraphAlgorithm.findShortestPath(krtkoland, towers[0], warehouses[0]).get();
			Utils.printPath(shortestPath);
			Assertions.assertIterableEquals(
					Arrays.asList(towers[0], bunkers[0], warehouses[0]),
					shortestPath
			);

		}
		{
			KrtkolandImpl krtkoland = new KrtkolandImpl();
			KrtkolandFactory.addSimplePath(krtkoland, towers[3], bunkers[1], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[2], bunkers[1], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[3], bunkers[2], bunkers[5], bunkers[6], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[1], bunkers[1], bunkers[0], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[1], bunkers[1], bunkers[2], warehouses[0]);
			//printAllEdges(krtkoland);

			updateToFullGraph(krtkoland, 1000);

			List<Room> shortestPath = DijkstraGraphAlgorithm.findShortestPath(krtkoland, towers[1], warehouses[0]).get();
			Utils.printPath(shortestPath);
			Assertions.assertIterableEquals(
					Arrays.asList(towers[1], bunkers[1], warehouses[0]),
					shortestPath
			);
		}
		{
			KrtkolandImpl krtkoland = new KrtkolandImpl();
			KrtkolandFactory.addSimplePath(krtkoland, towers[3], bunkers[4], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[2], bunkers[4], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[3], bunkers[2], bunkers[5], bunkers[6], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[1], bunkers[1], bunkers[0], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[1], bunkers[1], bunkers[2], warehouses[0]);
			//printAllEdges(krtkoland);

			updateToFullGraph(krtkoland, 1000);

			List<Room> shortestPath = DijkstraGraphAlgorithm.findShortestPath(krtkoland, towers[1], warehouses[0]).get();
			Utils.printPath(shortestPath);
			Assertions.assertIterableEquals(
					Arrays.asList(towers[1], bunkers[1], bunkers[0], warehouses[0]),
					shortestPath
			);
		}
		{
			KrtkolandImpl krtkoland = new KrtkolandImpl();
			KrtkolandFactory.addSimplePath(krtkoland, towers[3], bunkers[4], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[2], bunkers[4], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[3], bunkers[2], bunkers[5], bunkers[6], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[1], bunkers[1], bunkers[0], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[1], bunkers[1], bunkers[2], warehouses[0]);
			KrtkolandFactory.addSimplePath(krtkoland, towers[1], bunkers[0], warehouses[0]);
			//printAllEdges(krtkoland);

			updateToFullGraph(krtkoland, 1);

			List<Room> shortestPath = DijkstraGraphAlgorithm.findShortestPath(krtkoland, towers[1], warehouses[0]).get();
			Utils.printPath(shortestPath);
			Assertions.assertIterableEquals(
					Arrays.asList(towers[1], warehouses[0]),
					shortestPath
			);
		}
	}

	@Test
	public void testUtils() {
		KrtkolandImpl krtkoland = new KrtkolandImpl();
		KrtkolandFactory.addSimplePath(krtkoland, towers[0], bunkers[2], warehouses[0]);
		KrtkolandFactory.addSimplePath(krtkoland, towers[0], bunkers[1], warehouses[0]);
		KrtkolandFactory.addSimplePath(krtkoland, towers[0], bunkers[1], bunkers[2], warehouses[0]);
		KrtkolandFactory.addSimplePath(krtkoland, towers[3], bunkers[2], bunkers[5], bunkers[6], warehouses[0]);

		// 18 orientovaných hrán, 9 keby sa brali ako neorientované
		assertEquals(18, krtkoland.getEdges().size());

		updateToFullGraph(krtkoland, 1);
		// skontrolujeme či po updateToFullGraph je naozaj prepojený každý vrchol s každým: N * (N - 1) počet hrán v orientovanom grafe:
		assertEquals(krtkoland.getVertices().size() * (krtkoland.getVertices().size() - 1), krtkoland.getEdges().size());
	}

	static class Utils {
		public static void printAllEdges(KrtkolandImpl g) {
			System.out.println(" =========================================== Vsetky hrany =======================================");
			g.getEdges().forEach(e -> System.out.println(e.getSource().getId().getValue() + " - " + e.getTarget().getId().getValue()));
		}

		public static void printPath(List<Room> shortestPath) {
			System.out.println(
					shortestPath.stream().map(r -> r.getId().getValue()).collect(Collectors.joining(" - "))
			);
		}


	}
}
