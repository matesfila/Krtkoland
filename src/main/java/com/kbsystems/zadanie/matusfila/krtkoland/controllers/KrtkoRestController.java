package com.kbsystems.zadanie.matusfila.krtkoland.controllers;

import com.kbsystems.zadanie.matusfila.krtkoland.models.LandPath;
import com.kbsystems.zadanie.matusfila.krtkoland.models.Tower;
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
    public String getTest(Tower t) {
        return "toto je testovacia metoda";
    }

    @GetMapping("/getBestPath")
    public LandPath getBestPath(Tower t) {
        return krtkoService.theBestTimeAndPath(t);
    }

}
