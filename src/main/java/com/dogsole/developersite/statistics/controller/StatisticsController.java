package com.dogsole.developersite.statistics.controller;

import com.dogsole.developersite.statistics.dto.PontoResDTO;
import com.dogsole.developersite.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/statistics")
    public String gotoKgh(Model model) {
//        List<PontoResDTO> pontos = statisticsService.countUsersByCreatedAt();
        List<PontoResDTO> pontos = statisticsService.countUsersByBirthYear();
        model.addAttribute("pontos",pontos);
        return "/statistics/graph";
    }
}
