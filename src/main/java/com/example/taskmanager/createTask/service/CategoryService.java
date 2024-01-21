package com.example.taskmanager.createTask.service;

import com.example.taskmanager.application.domain.Category;
import com.example.taskmanager.createTask.dto.response.CategoryResponseDTO;
import com.example.taskmanager.createTask.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {

    private final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<CategoryResponseDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOs = categories.stream()
                .map(category -> new CategoryResponseDTO(category.getId(), category.getName(),
                        category.getCode()))
                .collect(Collectors.toList());
        LOG.info("getAll - number of categories: " + categoryResponseDTOs.size());

        return categoryResponseDTOs;
    }
}
