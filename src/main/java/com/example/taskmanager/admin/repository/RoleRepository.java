package com.example.taskmanager.admin.repository;

import com.example.taskmanager.application.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("AdminRoleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}