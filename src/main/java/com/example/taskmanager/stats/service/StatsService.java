package com.example.taskmanager.stats.service;

import com.example.taskmanager.application.component.CurrentUser;
import com.example.taskmanager.application.domain.Type;
import com.example.taskmanager.stats.dto.StatsDTO;
import com.example.taskmanager.stats.repository.StatsRepository;
import com.example.taskmanager.stats.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatsService {

    private final StatsRepository statsRepository;
    private final TypeRepository typeRepository;
    private final CurrentUser currentUser;

    public List<StatsDTO> getItemsByCategoryAndTypeStatistic() {

        log.info("Categories reading started");
        Map<String, StatsDTO> byCategory = findByCategory();
        Map<String, StatsDTO> byCategoryAndType = findByCategoryAndType();
        log.info("Categories reading completed");

        if(byCategory.isEmpty()) {
            return Collections.emptyList();
        }
        enterItemsNumberForTypeInRelatedCategoryDTO(byCategoryAndType, byCategory);

        List<StatsDTO> itemsByCategoryAndTypeStatisticDTOs = new ArrayList<>();
        itemsByCategoryAndTypeStatisticDTOs.addAll(byCategory.values());

        return itemsByCategoryAndTypeStatisticDTOs;
    }

    private Map<String, StatsDTO> findByCategory() {

        List<Object[]> itemsNumberByCategoryArr = statsRepository.findAppaItemsNumberByCategory(currentUser.getId());
        List<StatsDTO> itemsNumberByCategories = convertNativeResultToPOJO(
                itemsNumberByCategoryArr);
        Map<String, StatsDTO> itemByCategoryAndTypePairs = convertListToMap(
                itemsNumberByCategories);

        return itemByCategoryAndTypePairs;
    }

    private Map<String, StatsDTO> findByCategoryAndType() {

        String typeCode = "AT1";
        Type type = typeRepository.findByCode(typeCode);
        List<Object[]> itemsNumberByCategoryAndTypeArr = statsRepository
                .findAppaItemsNumberByCategoryAndType(currentUser.getId(), type.getId());
        List<StatsDTO> itemsNumberByCategoriesAndTypes = convertNativeResultToPOJO(
                itemsNumberByCategoryAndTypeArr);
        Map<String, StatsDTO> itemByCategoryAndTypePairs = convertListToMap(
                itemsNumberByCategoriesAndTypes);

        return itemByCategoryAndTypePairs;
    }

    private List<StatsDTO> convertNativeResultToPOJO(
            List<Object[]> itemsNumberByCategories) {

        List<StatsDTO> itemsNumberByCategory = itemsNumberByCategories
                .stream()
                .map(itemsNumberByCategoryArr -> new StatsDTO(
                        String.valueOf(itemsNumberByCategoryArr[0]),
                        Integer.valueOf(String.valueOf(itemsNumberByCategoryArr[1]))))
                .collect(Collectors.toList());

        return itemsNumberByCategory;
    }

    private Map<String, StatsDTO> convertListToMap(
            List<StatsDTO> itemsNumberByCategories) {

        Map<String, StatsDTO> itemByCategoryAndTypePairs = new TreeMap<>();
        itemsNumberByCategories.forEach(itemNumberByCategory -> {
            itemByCategoryAndTypePairs.put(itemNumberByCategory.getAppaCategoryName(), itemNumberByCategory);
        });

        return itemByCategoryAndTypePairs;
    }

    private void enterItemsNumberForTypeInRelatedCategoryDTO(
            Map<String, StatsDTO> byCategoryAndType,
            Map<String, StatsDTO> byCategory) {

        byCategoryAndType.forEach((categoryName, itemsByCategoryAndTypeDTO) -> {
            StatsDTO itemsByCategoryAndTypeDTO2 = byCategory.get(categoryName);
            itemsByCategoryAndTypeDTO2.setAppaItemsByTypeNumber(itemsByCategoryAndTypeDTO.getAppaItemsNumber());
        });
    }
}
