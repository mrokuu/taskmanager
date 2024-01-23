package com.example.taskmanager.stats.repository;

import com.example.taskmanager.application.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByCode(String code);
}
