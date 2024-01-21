package com.example.taskmanager.createTask.controller;

import com.example.taskmanager.createTask.dto.response.CategoryResponseDTO;
import com.example.taskmanager.createTask.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAll() {
        List<CategoryResponseDTO> categoryResponseDTOs = categoryService.getAll();
        return new ResponseEntity<>(categoryResponseDTOs, HttpStatus.OK);
    }

}

