package com.example.taskmanager.stats.repository;

import com.example.taskmanager.application.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatsRepository extends JpaRepository<Task,Long> {

    @Query(value = "select ac.name, count(aci.items_id) from appa_categories_items aci "
            + "inner join appa_categories ac on ac.id=aci.categories_id "
            + "inner join appa_items ai on ai.id=aci.items_id "
            + "where ai.creator_id=:currentUserId "
            + "and not exists (select 1 from appa_items_users aiu where aiu.items_id=ai.id and aiu.users_id=:currentUserId)"
            + "group by aci.categories_id, ac.name", nativeQuery = true)
    List<Object[]> findAppaItemsNumberByCategory(@Param("currentUserId") Long currentUserId);

    @Query(value = "select ac.name, count(aci.items_id) from appa_categories_items aci "
            + "inner join appa_categories ac on ac.id=aci.categories_id "
            + "inner join appa_items ai on ai.id=aci.items_id " + "inner join appa_types at on at.id=ai.appa_types_id "
            + "where ai.creator_id=:currentUserId and at.id=:appaGraphTypeId "
            + "and not exists (select 1 from appa_items_users aiu where aiu.items_id=ai.id and aiu.users_id=:currentUserId)"
            + "group by aci.categories_id, ac.name", nativeQuery = true)
    List<Object[]> findAppaItemsNumberByCategoryAndType(@Param("currentUserId") Long currentUserId,
                                                        @Param("appaGraphTypeId") Long appaGraphTypeId);

}
