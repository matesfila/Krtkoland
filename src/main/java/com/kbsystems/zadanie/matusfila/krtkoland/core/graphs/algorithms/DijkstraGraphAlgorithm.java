package com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.algorithms;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.Edge;
import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.Graph;
import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.Vertex;

import java.util.*;

/**
 * Počíta najkratšiu cestu v grafe pomocou Dijkstrovho algoritmu.
 * <p>
 * Zdroj:
 * <a href="https://medium.com/@kirti07arora/dijkstras-algorithm-in-java-a-journey-through-shortest-paths-cc2fd76104b2">...</a>
 */
public class DijkstraGraphAlgorithm {
    /*

Popis algoritmu dohľadávania najkratšej cesty podľa Dijkstru:

1. Set initial distances for all vertices: 0 for the source vertex, and infinity for all the other.

2. Choose the unvisited vertex with the shortest distance from the start to be the current vertex.
So the algorithm will always start with the source as the current vertex.

3. For each of the current vertex's unvisited neighbor vertices, calculate the distance from the source
and update the distance if the new, calculated, distance is lower.

4. We are now done with the current vertex, so we mark it as visited. A visited vertex is not checked again.

5 Go back to step 2 to choose a new current vertex, and keep repeating these steps until all vertices are visited.

6. In the end we are left with the shortest path from the source vertex to every other vertex in the graph.

     */


    public static <V extends Vertex, E extends Edge<V>> Map<V, V> computeShortestPaths(Graph<V, E> graph, V source) {

        final Map<V, Float> distances = new HashMap<>();
        final Map<V, V> predecessors = new HashMap<>();
        final PriorityQueue<V> unvisitedVertices = new PriorityQueue<>(Comparator.comparing(distances::get));

        graph.getVertices().forEach(v -> distances.put(v, Float.POSITIVE_INFINITY));
        distances.remove(source);
        distances.put(source, 0f);

        unvisitedVertices.addAll(graph.getVertices());

        while (!unvisitedVertices.isEmpty()) {

            V u = unvisitedVertices.poll();

            graph.allEdges(u).stream()
                    .filter(e -> unvisitedVertices.contains(e.getTarget()))
                    .forEach(edge -> {
                        V neighbor = edge.getTarget();
                        float newDist = distances.get(u) + edge.getWeight();
                        if (newDist < distances.get(neighbor)) {
                            distances.remove(neighbor);
                            distances.put(neighbor, newDist);
                            predecessors.remove(neighbor);
                            predecessors.put(neighbor, u);
                            unvisitedVertices.remove(neighbor);
                            unvisitedVertices.add(neighbor);
                        }
                    });

//            for (E edge : graph.allEdges(u)) {
//                V neighbor = edge.getTarget();
//                float newDist = distances.get(u) + edge.getWeight();
//                if (newDist < distances.get(neighbor)) {
//                    distances.put(neighbor, newDist);
//                    predecessors.put(neighbor, u);
//                    unvisitedVertices.remove(neighbor); // Remove if exists to update priority
//                    unvisitedVertices.add(neighbor);
//                }
//            }
        }

        return predecessors;
    }

    public static <V extends Vertex> List<V> reconstructPath(Map<V, V> predecessors, V target) {
        List<V> path = new LinkedList<>();
        for (V vertex = target; vertex != null; vertex = predecessors.get(vertex)) {
            path.add(0, vertex);
        }
        return path;
    }

    public static <V extends Vertex, E extends Edge<V>> Optional<List<V>> findShortestPath(Graph<V, E> graph, V source, V target) {
        List<V> path = reconstructPath(computeShortestPaths(graph, source), target);
        if (path.size() > 1) {
            return Optional.of(path);
        } else {
            return Optional.empty();
        }
    }
}
