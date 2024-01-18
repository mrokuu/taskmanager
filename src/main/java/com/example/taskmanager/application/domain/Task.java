package com.example.taskmanager.application.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@Service
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable, Comparable<Task> {

    @Id
    @SequenceGenerator(name = "TaskSequence", sequenceName = "task_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TaskSequence")
    private Long id;

    @Column(length = 255)
    private String name;

    @Column(length = 4000)
    private String description;

    @Column(length = 4000)
    private String comment;

    @Column(name = "date_from")
    private Instant dateTimeFrom;

    @Column(name = "date_to")
    private Instant dateTimeTo;

    @Column(name = "amount")
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    private String image;

    @Column(name = "fake_id")
    private Long fakeId;


    @ManyToOne
    @JoinColumn(name = "types_id")
    private Type type;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "categories_tasks", joinColumns = { @JoinColumn(name = "task_id") }, inverseJoinColumns = {
            @JoinColumn(name = "categories_id") })
    private List<Category> Categories = new ArrayList<Category>();

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "attributes_tasks", joinColumns = { @JoinColumn(name = "task_id") }, inverseJoinColumns = {
            @JoinColumn(name = "attributes_id") })
    private List<Attribute> Attributes = new ArrayList<Attribute>();

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "tasks_users", joinColumns = { @JoinColumn(name = "tasks_id") }, inverseJoinColumns = {
            @JoinColumn(name = "users_id") })
    private List<User> tasksUsers = new ArrayList<User>();



    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? super.hashCode() : id.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task other = (Task) obj;
        if (id == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (id.equals(other.getId())) {
            return true;
        }

        return false;
    }

    @Override
    public int compareTo(Task o) {
        return getName().compareTo(((Task) o).getName());
    }

    @PrePersist
    protected void onCreate() {
        if (this.creationDate == null) {
            LocalDateTime currentDate = LocalDateTime.now();
            this.creationDate = currentDate;
            this.modificationDate = currentDate;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.modificationDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
