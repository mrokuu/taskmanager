package com.example.taskmanager.login.repository;

import com.example.taskmanager.application.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select max(fakeId) from Task ai where ai.creator.id=:creatorId")
    Long maxFakeId(@Param("creatorId") Long creatorId);
}
