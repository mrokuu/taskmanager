package com.example.taskmanager.application.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Service
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category extends AbstractEntity<Long> implements Comparable<Category> {

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String code;

    @Column(length = 4000)
    private String description;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "categories_tasks", joinColumns = {
            @JoinColumn(name = "categories_id") }, inverseJoinColumns = { @JoinColumn(name = "task_id") })
    private Set<Task> tasks = new HashSet<Task>();


    @Override
    public int compareTo(Category o) {
        return getName().compareTo(((Category) o).getName());
    }
}

