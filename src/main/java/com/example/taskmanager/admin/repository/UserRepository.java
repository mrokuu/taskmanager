package com.example.taskmanager.admin.repository;

import com.example.taskmanager.application.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("AdminUserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

}
