package com.example.taskmanager.createTask.dto;

import com.example.taskmanager.application.domain.StatusEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class BaseItemDTO {

    private Long id;

    @NotNull
    @Size(min = 8, max = 40)
    private String name;

    @NotNull
    private Integer amount;

    private String comment;

    private String description;

    @NotNull
    private Instant dateTimeFrom;

    @NotNull
    private Instant dateTimeTo;

    @NotEmpty
    private List<Long> selectedAttributes;

    @NotEmpty
    private List<Long> selectedCategories;

    @NotNull
    private Long selectedType;

    @NotNull
    private StatusEnum status;

    @NotNull
    private String image;

    public BaseItemDTO(Long id, String name, Integer amount, String comment, String description,
                       Instant dateTimeFrom, Instant dateTimeTo, List<Long> selectedAttributes,
                       List<Long> selectedCategories, Long selectedType, StatusEnum status, String image) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.comment = comment;
        this.description = description;
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
        this.selectedAttributes = selectedAttributes;
        this.selectedCategories = selectedCategories;
        this.selectedType = selectedType;
        this.status = status;
        this.image = image;
    }

    public BaseItemDTO() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
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

    public List<Long> getSelectedAttributes() {
        return selectedAttributes;
    }

    public List<Long> getSelectedCategories() {
        return selectedCategories;
    }

    public Long getSelectedType() {
        return selectedType;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {

        return new ToStringBuilder(this).append("id", id).append("name", name).append("amount", amount)
                .append("comment", comment).append("description", description).append("dateTimeFrom", dateTimeFrom)
                .append("dateTimeTo", dateTimeTo)
                .append("selectedAttributes",
                        selectedAttributes.stream().map(attributeId -> attributeId.toString())
                                .collect(Collectors.joining(", ")))
                .append("selectedCategories",
                        selectedCategories.stream().map(attributeId -> attributeId.toString())
                                .collect(Collectors.joining(", ")))
                .append("selectedType", selectedType).append("status", status.getValue()).append("image", image)
                .toString();
    }
}
