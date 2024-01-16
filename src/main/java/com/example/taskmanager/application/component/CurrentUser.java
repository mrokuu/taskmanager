package com.example.taskmanager.application.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class CurrentUser {

    private Long id;
    private String firstName;
    private String lastName;
    private String profileName;
    public Long itemFakeId = 0L;

    public synchronized long reset() {
        itemFakeId = 0L;
        return itemFakeId;
    }

    public synchronized long inc() {
        return ++itemFakeId;
    }

    public synchronized long dec() {
        return --itemFakeId;
    }

    public synchronized void setFakeId(Long fakeId) {
        this.itemFakeId = fakeId != null ? fakeId : 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

