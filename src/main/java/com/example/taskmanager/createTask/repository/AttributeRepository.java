package com.example.taskmanager.createTask.repository;

import com.example.taskmanager.application.domain.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttributeRepository extends JpaRepository<Attribute, Long> {

}
