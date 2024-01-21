package com.example.taskmanager.createTask.repository;

import com.example.taskmanager.application.domain.Task;
import com.example.taskmanager.application.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByCreatorAndFakeId(User creator, Long fakeId);

    @Query("select ai from Item ai where ai.creator.id=:#{#currentUser.id} " +
            "and (not exists (select 1 from User agu where ai member of agu.appaItems " +
            "and :currentUser member of ai.appaItemsUsers))")
    List<Task> findAllLoggedUser(@Param("currentUser") User currentUser, Sort sort);

    @Query("select count(ai) from Item ai where ai.creator.id=:#{#currentUser.id}  " +
            "and (not exists (select 1 from User agu where ai member of agu.appaItems " +
            "and :currentUser member of ai.appaItemsUsers))")
    long countByCreator(@Param("currentUser") User currentUser);
}
