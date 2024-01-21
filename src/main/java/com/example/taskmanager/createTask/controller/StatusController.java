package com.example.taskmanager.createTask.controller;

import com.example.taskmanager.createTask.dto.response.StatusResponseDTO;
import com.example.taskmanager.createTask.service.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<StatusResponseDTO>> getAll() {
        List<StatusResponseDTO> statusResponseDTOs = statusService.getAll();
        return new ResponseEntity<>(statusResponseDTOs, HttpStatus.OK);
    }

}

