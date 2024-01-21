package com.example.taskmanager.createTask.dto.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TypeResponseDTO {

    private final Long id;
    private final String name;
    private final String code;

    public TypeResponseDTO(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name)
                .append("code", code).toString();
    }
}

