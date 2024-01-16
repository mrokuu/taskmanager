package com.example.taskmanager.admin.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

}
