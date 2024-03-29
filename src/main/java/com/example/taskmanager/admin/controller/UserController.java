package com.example.taskmanager.admin.controller;

import com.example.taskmanager.admin.dto.request.UserRequestDTO;
import com.example.taskmanager.admin.dto.response.UserResponseDTO;
import com.example.taskmanager.admin.service.UserService;
import com.example.taskmanager.admin.validator.PasswordChangeValid;
import com.example.taskmanager.admin.validator.PasswordCreateValid;
import com.example.taskmanager.application.exception.UserInputException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<Void> saveUser(@Valid @RequestBody @PasswordCreateValid UserRequestDTO userRequestDTO){
        userService.saveUser(userRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/current")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody @PasswordChangeValid UserRequestDTO userRequestDTO)
            throws UserInputException {

        userService.updateUser(userRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<UserResponseDTO> getCurrentUser() {

        UserResponseDTO userResponseDTO = userService.getCurrentUser();
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/current/roles/{newRoleName}")
    public ResponseEntity<Void> updateCurrentUserRoleName(@PathVariable("newRoleName") String newRoleName) {

        userService.updateCurrentUserRoleName(newRoleName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
