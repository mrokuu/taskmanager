package com.example.taskmanager.createTask.controller;

import com.example.taskmanager.createTask.dto.response.TypeResponseDTO;
import com.example.taskmanager.createTask.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public ResponseEntity<List<TypeResponseDTO>> getAll() {
        List<TypeResponseDTO> typeResponseDTOs = typeService.getAll();
        return new ResponseEntity<>(typeResponseDTOs, HttpStatus.OK);
    }
}

