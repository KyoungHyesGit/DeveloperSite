package com.dogsole.developersite.statistics.controller;

import com.dogsole.developersite.statistics.dto.PontoResDTO;
import com.dogsole.developersite.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/api/statistics")
public class StatisticsRestController {

    private final StatisticsService statisticsService;

    @GetMapping("/count-by-created-at")
    public List<PontoResDTO> countUsersByCreatedAt() {
        return statisticsService.countUsersByCreatedAt();
    }

    @GetMapping("/count-by-birth-year")
    public List<PontoResDTO> countUsersByBirthYear() {
        return statisticsService.countUsersByBirthYear();
    }

}
