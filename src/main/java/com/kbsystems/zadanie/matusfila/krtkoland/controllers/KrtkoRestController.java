package com.kbsystems.zadanie.matusfila.krtkoland.controllers;

import com.kbsystems.zadanie.matusfila.krtkoland.models.BestTimeAndPathResult;
import com.kbsystems.zadanie.matusfila.krtkoland.services.KrtkoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * matus 5/17/2025
 */
@RestController
public class KrtkoRestController {

    @Autowired
    KrtkoService krtkoService;

    @GetMapping("/getTest")
    public String getTest(String s) {
        return "toto je testovacia metoda " + s;
    }

    @GetMapping("/getBestPath")
    public BestTimeAndPathResult getBestPath(String source, String target) {
        return krtkoService.theBestTimeAndPath(source, target);
    }

}
