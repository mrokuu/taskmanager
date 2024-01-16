package com.example.application.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "users")
public class User extends AbstractEntity<Long> {

    private String firstName;

    private String lastName;

    private String profileName;

    private String email;

    private String password;

    private boolean active;

    private String token;

    private Date tokenTime;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    protected List<Role> roles = new ArrayList<Role>();

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "appa_items_users", joinColumns = { @JoinColumn(name = "users_id") }, inverseJoinColumns = {
            @JoinColumn(name = "items_id") })
    private Set<Task> appaItems = new HashSet<Task>();

    public Set<Task> getAppaItems() {
        return appaItems;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(Date tokenTime) {
        this.tokenTime = tokenTime;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void generateToken() {

        String token = UUID.randomUUID().toString();
        setToken(token);
        setTokenTime(new Date());
    }



}