package com.example.taskmanager.login.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SecurityResponseDTO {


    private final String email;
    private final List<String> roles = new ArrayList<>();

    public SecurityResponseDTO(String email, List<String> roles) {
        this.email = email;
        this.roles.addAll(roles);
    }

}
