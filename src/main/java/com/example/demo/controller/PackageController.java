package com.example.demo.controller;

import com.example.demo.repository.Score;
import hex.genmodel.easy.exception.PredictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PackageController {


    @Autowired
    private Score score;

    @GetMapping("/score")
    public double getScore() throws IOException, PredictException {
        return score.getScore();

    }
}