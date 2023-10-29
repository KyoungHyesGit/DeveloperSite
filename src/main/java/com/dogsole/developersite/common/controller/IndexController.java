package com.dogsole.developersite.common.controller;

import com.dogsole.developersite.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/")
//    public String gotoKgh() {
//        return "redirect:/job_post/jobList";
//    }
    public String gotoKgh() {
        return "redirect:/jobPostTemp/tempList";

    }
}
