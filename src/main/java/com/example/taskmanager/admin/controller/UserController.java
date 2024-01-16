package com.example.taskmanager.admin.controller;

import com.example.taskmanager.admin.dto.request.UserRequestDTO;
import com.example.taskmanager.admin.service.UserService;
import com.example.taskmanager.admin.validator.PasswordCreateValid;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    public ResponseEntity<Void> saveUser(@Valid @RequestBody @PasswordCreateValid UserRequestDTO userRequestDTO){
        userService.saveUser(userRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
