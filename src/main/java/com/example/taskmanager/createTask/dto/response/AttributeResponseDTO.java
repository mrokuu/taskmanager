package com.example.taskmanager.createTask.dto.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AttributeResponseDTO {

    private final Long id;
    private final String name;
    private final String value;

    public AttributeResponseDTO(Long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name)
                .append("value", value).toString();
    }
}
