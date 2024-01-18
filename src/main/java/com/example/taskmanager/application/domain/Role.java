package com.example.taskmanager.application.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Service
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity<Long> {

    @Column(name = "role_name", length = 30)
    private String roleName;

    @Column(length = 255)
    private String description;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<User>();


}
