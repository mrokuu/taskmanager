package com.example.taskmanager.task.dto.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Instant;

public class TaskResponseDTO {

    private final Long id;
    private final String name;
    private final String description;
    private final Instant dateTimeFrom; // Zmiana z LocalDateTime do Instant nastąpiła w momencie pojawienia się frontendu w Angular
    private final Instant dateTimeTo; // Zmiana z LocalDateTime do Instant nastąpiła w momencie pojawienia się frontendu w Angular

    public TaskResponseDTO(Long id, String name, String description, Instant dateTimeFrom,
                           Instant dateTimeTo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Instant getDateTimeFrom() {
        return dateTimeFrom;
    }

    public Instant getDateTimeTo() {
        return dateTimeTo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name)
                .append("description", description).append("dateTimeFrom", dateTimeFrom)
                .append("dateTimeTo", dateTimeTo).toString();
    }
}
