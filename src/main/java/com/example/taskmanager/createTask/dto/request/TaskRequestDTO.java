package com.example.taskmanager.createTask.dto.request;

import com.example.taskmanager.application.domain.StatusEnum;
import com.example.taskmanager.createTask.dto.BaseItemDTO;

import java.time.Instant;
import java.util.List;

public class TaskRequestDTO extends BaseItemDTO {

    public TaskRequestDTO(Long id, String name, Integer amount, String comment, String description,
                          Instant dateTimeFrom, Instant dateTimeTo, List<Long> selectedAttributes,
                          List<Long> selectedCategories, Long selectedType, StatusEnum status, String image) {
        super(id, name, amount, comment, description, dateTimeFrom, dateTimeTo, selectedAttributes, selectedCategories,
                selectedType, status, image);
    }

    public TaskRequestDTO() {

    }
}
