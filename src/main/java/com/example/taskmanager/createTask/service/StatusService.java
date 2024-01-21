package com.example.taskmanager.createTask.service;

import com.example.taskmanager.application.domain.StatusEnum;
import com.example.taskmanager.createTask.dto.response.StatusResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    public List<StatusResponseDTO> getAll() {
        List<StatusEnum> statusesEnums = Arrays.asList(StatusEnum.values());
        List<StatusResponseDTO> statusesDTOs = statusesEnums.stream()
                .map(status -> new StatusResponseDTO(status.name(), status.getValue()))
                .collect(Collectors.toList());

        LOG.info("getAll - number of statuses: " + statusesDTOs.size());

        return statusesDTOs;
    }
}

