package com.dogsole.developersite.common.controller;

import com.dogsole.developersite.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class IndexController {
    @Autowired
    private StatisticsService statisticsService;


}
