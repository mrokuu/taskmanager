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
@Table(name = "types")
@Getter
@Service
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Type extends AbstractEntity<Long> implements Comparable<Type> {

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String code;

    @Column(length = 4000)
    private String description;

    @OneToMany(mappedBy = "type", cascade = { CascadeType.MERGE })
    private Set<Task> tasks = new HashSet<Task>();


    @Override
    public int compareTo(Type o) {
        return getName().compareTo(((Type) o).getName());
    }
}

