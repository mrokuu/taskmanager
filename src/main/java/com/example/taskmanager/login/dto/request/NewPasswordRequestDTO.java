package com.example.taskmanager.login.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordRequestDTO {

    @NotNull
    @Size(min = 6, max = 10)
    private String newPassword;

    @NotNull
    private String newRepeatedPassword;

    @NotNull
    private String token;
}
