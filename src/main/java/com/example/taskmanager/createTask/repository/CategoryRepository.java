package com.example.taskmanager.createTask.repository;

import com.example.taskmanager.application.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
