package com.example.taskmanager.createTask.repository;

import com.example.taskmanager.application.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {


}
