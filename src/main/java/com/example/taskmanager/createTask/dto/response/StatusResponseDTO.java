package com.example.taskmanager.createTask.dto.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class StatusResponseDTO {

    private final String name;
    private final String value;

    public StatusResponseDTO(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name)
                .append("value", value).toString();
    }
}

