package com.example.taskmanager.application.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "attributes")
@Getter
@Setter
@Service
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attribute extends AbstractEntity<Long> implements Comparable<Attribute> {

    @Column(length = 255)
    private String name;

    @Column(length = 4000)
    private String value;
    @Override
    public int compareTo(Attribute o) {
        return getName().compareTo(((Attribute) o).getName());
    }

}
