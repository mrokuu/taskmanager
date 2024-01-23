package com.example.taskmanager.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StatsDTO {

    private final String appaCategoryName;
    private final Integer appaItemsNumber;
    // Exclusion of final rule because we use setter after selecting value from map
    private Integer appaItemsByTypeNumber;

    public StatsDTO(String appaCategoryName, Integer appaItemsNumber) {
        this.appaCategoryName = appaCategoryName;
        this.appaItemsNumber = appaItemsNumber;
        this.appaItemsByTypeNumber = 0;
    }

    public void setAppaItemsByTypeNumber(Integer appaItemsByTypeNumber) {
        this.appaItemsByTypeNumber = appaItemsByTypeNumber;
    }
}
