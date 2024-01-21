package com.example.taskmanager.createTask.service;

import com.example.taskmanager.application.domain.Attribute;
import com.example.taskmanager.createTask.dto.response.AttributeResponseDTO;
import com.example.taskmanager.createTask.repository.AttributeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttributeService {

    private final Logger LOG = LoggerFactory.getLogger(AttributeService.class);

    private final AttributeRepository attributeRepository;

    public AttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }


    public List<AttributeResponseDTO> getAll() {

        List<Attribute> attributes = attributeRepository.findAll();
        List<AttributeResponseDTO> attributeResponseDTOs = new ArrayList<>(attributes.size());

        for (Attribute attribute : attributes) {
            AttributeResponseDTO attributeResponseDTO = new AttributeResponseDTO(attribute.getId(),
                    attribute.getName(), attribute.getValue());
            attributeResponseDTOs.add(attributeResponseDTO);
        }

        LOG.info("getAll - number of attributes: " + attributeResponseDTOs.size());

        return attributeResponseDTOs;
    }
}
