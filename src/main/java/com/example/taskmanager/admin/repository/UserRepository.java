package com.example.taskmanager.admin.repository;

import com.example.application.domain.User;
import com.example.taskmanager.admin.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("AdminUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

}
