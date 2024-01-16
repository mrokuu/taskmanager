package com.example.taskmanager.admin.dto.response;

import lombok.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class UserResponseDTO {

    private final Long id;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String profileName;
    private final String password;
    private final String roleName;

    public UserResponseDTO(Long id, String email, String firstName, String lastName, String profileName,
                           String password, String roleName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileName = profileName;
        this.password = password;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    // Getters which seems to be unused are being used by fastermxl
    // library to read values and convert them to the http request.
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("firstName", firstName)
                .append("lastName", lastName).append("profileName", profileName)
                .append("roleName", roleName).toString();
    }

}
