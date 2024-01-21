package com.example.taskmanager.createTask.service;

import com.example.taskmanager.application.domain.Type;
import com.example.taskmanager.createTask.dto.response.TypeResponseDTO;
import com.example.taskmanager.createTask.repository.TypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeService {

    private static final Logger LOG = LoggerFactory.getLogger(TypeService.class);

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<TypeResponseDTO> getAll() {
        List<Type> types = typeRepository.findAll();
        List<TypeResponseDTO> typesDTOs = types.stream()
                .map(type -> new TypeResponseDTO(type.getId(), type.getName(), type.getCode()))
                .collect(Collectors.toList());
        LOG.info("getAll - number of types: " + typesDTOs.size());

        return typesDTOs;
    }
}
