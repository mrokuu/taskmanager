package com.example.taskmanager.createTask.repository;

import com.example.taskmanager.application.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
