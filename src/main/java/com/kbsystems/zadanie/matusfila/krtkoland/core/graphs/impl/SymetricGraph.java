package com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.impl;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.AbstractGraph;
import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.Edge;
import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.interfaces.Vertex;


/**
 * Symetrický graf sa bude zatiaľ reprezentovať tak, že pri pridávaní hrany a,b do asymetrického grafu sa
 * automaticky pridá aj hrana b,a.
 */
@Deprecated
public class SymetricGraph <V extends Vertex, E extends Edge<V>> extends AbstractGraph<V, E> {

    public SymetricGraph() {
    }

    @Override
    public boolean isEdge(V source, V target) {
        return super.isEdge(source, target) || super.isEdge(target, source);
    }


    //    @Override
//    public Set<E> allEdges(V source) {
//        return getEdges().stream()
//                .filter(e -> Objects.equals(e.getSource(), source) || Objects.equals(e.getTarget(), source))
//                .collect(Collectors.toUnmodifiableSet());
//    }

}
