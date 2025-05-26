package com.kbsystems.zadanie.matusfila.krtkoland.controllers;

import com.kbsystems.zadanie.matusfila.krtkoland.models.BestTimeAndPathResult;
import com.kbsystems.zadanie.matusfila.krtkoland.services.KrtkoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * matus 5/17/2025
 */
@RestController
@RequestMapping("/api")
public class KrtkoRestController {

    @Autowired
    KrtkoService krtkoService;

    @GetMapping("/healthCheck")
    public String healthCheck(String s) {
        return "toto je testovacia metoda " + s;
    }

    @GetMapping("/getBestPath")
    public BestTimeAndPathResult getBestPath(@RequestParam String source, @RequestParam String target) {
        return krtkoService.theBestTimeAndPath(source, target);
    }

}
