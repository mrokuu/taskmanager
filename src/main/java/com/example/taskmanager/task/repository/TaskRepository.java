package com.example.taskmanager.task.repository;

import com.example.taskmanager.application.domain.Task;
import com.example.taskmanager.application.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {


    @Query("select ai from Task ai where ai.creator.id=:#{#currentUser.id} " +
            "and (not exists (select 1 from User agu where ai member of agu.appaItems " +
            "and :currentUser member of ai.appaItemsUsers)) " +
            "and ( lower(ai.name) LIKE :searchValue "
            + "OR cast(ai.id as string) LIKE :searchValue "
            + "OR lower(ai.description) LIKE :searchValue "
            + "OR to_char(ai.dateTimeFrom,'DD/MM/YYYY HH:MI') LIKE :searchValue "
            + "OR to_char(ai.dateTimeTo,'DD/MM/YYYY HH:MI') LIKE :searchValue)")
    Page<Task> findAll(@Param("searchValue") String searchValue,
                       @Param("currentUser") User currentUser, Pageable pageable);

    @Query("select count(ai) from Task ai where ai.creator.id=:#{#currentUser.id} " +
            "and (not exists (select 1 from User agu where ai member of agu.appaItems " +
            "and :currentUser member of ai.appaItemsUsers)) and (lower(ai.name) LIKE :searchValue "
            + "OR cast(ai.id as string) LIKE :searchValue "
            + "OR lower(ai.description) LIKE :searchValue "
            + "OR to_char(ai.dateTimeFrom,'DD/MM/YYYY HH:MI') LIKE :searchValue "
            + "OR to_char(ai.dateTimeTo,'DD/MM/YYYY HH:MI') LIKE :searchValue)")
    int countAll(@Param("searchValue") String searchValue, @Param("currentUser") User currentUser);

    Task  findByCreatorAndFakeId(User creator, Long fakeId);
}
