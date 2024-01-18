package com.example.taskmanager.admin.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
@Setter
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



    @Override
    public String toString() {
        return new ToStringBuilder(this).append("firstName", firstName)
                .append("lastName", lastName).append("profileName", profileName)
                .append("roleName", roleName).toString();
    }

}
