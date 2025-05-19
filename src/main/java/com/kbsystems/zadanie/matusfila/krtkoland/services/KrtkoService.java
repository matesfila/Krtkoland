package com.kbsystems.zadanie.matusfila.krtkoland.services;

import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.ID;
import com.kbsystems.zadanie.matusfila.krtkoland.core.graphs.algorithms.DijkstraGraphAlgorithm;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.KrtkolandImpl;
import com.kbsystems.zadanie.matusfila.krtkoland.core.krtkoland.Room;
import com.kbsystems.zadanie.matusfila.krtkoland.models.BestTimeAndPathResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * matus 5/17/2025
 */
@Service
public class KrtkoService {

    private static final Logger log = LoggerFactory.getLogger(KrtkoService.class);

    private final KrtkolandImpl krtkoland;

    @Autowired
    public KrtkoService(KrtkolandImpl krtkoland) {
        this.krtkoland = krtkoland;
    }

    public BestTimeAndPathResult theBestTimeAndPath(String source, String target) {
        try {
            Optional<Room> s = krtkoland.vertexById(new ID(source));
            if (s.isEmpty()) {
                return new BestTimeAndPathResult(BestTimeAndPathResult.pathNotFountError);
            }

            Optional<Room> t = krtkoland.vertexById(new ID(target));
            if (t.isEmpty()) {
                return new BestTimeAndPathResult(BestTimeAndPathResult.pathNotFountError);
            }

            Optional<List<Room>> p = DijkstraGraphAlgorithm.findShortestPath(
                    krtkoland,
                    s.get(),
                    t.get()
            );

            if (p.isPresent()) {
                return new BestTimeAndPathResult(
                        krtkoland.computePathWeight(p.get()),
                        p.get().stream().map(r -> r.getId().getValue()).collect(Collectors.toList())
                );
            } else {
                return new BestTimeAndPathResult(BestTimeAndPathResult.pathNotFountError);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new BestTimeAndPathResult(BestTimeAndPathResult.pathError + ": " + e.getMessage());
        }
    }


}