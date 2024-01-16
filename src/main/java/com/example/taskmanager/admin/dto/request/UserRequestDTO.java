package com.example.taskmanager.admin.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class UserRequestDTO {

    @NotNull
    @Size(min = 1, max = 120)
    private String email;

    @NotNull
    @Size(min = 1, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;

    @NotNull
    @Size(min = 6, max = 20)
    private String profileName;

    private String oldPassword;

    private String newPassword;

    private String newRepeatedPassword;

    private String roleName;



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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewRepeatedPassword() {
        return newRepeatedPassword;
    }

    public void setNewRepeatedPassword(String newRepeatedPassword) {
        this.newRepeatedPassword = newRepeatedPassword;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isPasswordChangeDetected() {
        return this.newPassword != null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {

        return new ToStringBuilder(this).append("firstName", firstName)
                .append("lastName", lastName).append("profileName", profileName)
                .append("roleName", roleName).toString();
    }
}

