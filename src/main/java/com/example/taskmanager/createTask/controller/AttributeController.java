package com.example.taskmanager.createTask.controller;

import com.example.taskmanager.createTask.dto.response.AttributeResponseDTO;
import com.example.taskmanager.createTask.service.AttributeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/attributes")
public class AttributeController {

    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping
    public ResponseEntity<List<AttributeResponseDTO>> getAll() {
        List<AttributeResponseDTO> attributeResponseDTOs = attributeService.getAll();
        return new ResponseEntity<>(attributeResponseDTOs, HttpStatus.OK);
    }
}

