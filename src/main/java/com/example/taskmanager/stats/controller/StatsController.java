package com.example.taskmanager.stats.controller;


import com.example.taskmanager.stats.dto.StatsDTO;
import com.example.taskmanager.stats.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController("GraphItemController")
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    @GetMapping
    public ResponseEntity<List<StatsDTO>> getItemsByCategoryAndTypeStatistic() {
        List<StatsDTO> itemsByCategoryAndTypeStatisticDTOs = statsService
                .getItemsByCategoryAndTypeStatistic();
        return new ResponseEntity<>(itemsByCategoryAndTypeStatisticDTOs, HttpStatus.OK);
    }
}
