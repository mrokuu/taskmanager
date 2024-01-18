package com.example.taskmanager.login.controller;

import com.example.taskmanager.login.dto.request.TokenRequestDTO;
import com.example.taskmanager.login.dto.response.EmailResponseDTO;
import com.example.taskmanager.login.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Validated
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final RestTemplate restTemplate = new RestTemplate();


    @PostMapping("/password/token")
    public ResponseEntity<EmailResponseDTO> sendEmailWithToken(@Valid @RequestBody TokenRequestDTO tokenRequestDTO){

        EmailResponseDTO emailResponseDTO = userService.saveTokenAndSendEmail(tokenRequestDTO);
        return new ResponseEntity<>(emailResponseDTO, HttpStatus.OK);
    }
}
