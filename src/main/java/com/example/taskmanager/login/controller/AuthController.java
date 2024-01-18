package com.example.taskmanager.login.controller;

import com.example.taskmanager.login.dto.request.NewPasswordRequestDTO;
import com.example.taskmanager.login.dto.request.ResetPasswordRequestDTO;
import com.example.taskmanager.login.dto.request.TokenRequestDTO;
import com.example.taskmanager.login.dto.response.EmailResponseDTO;
import com.example.taskmanager.login.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/password/reset")
    public void resetPassword(@Valid ResetPasswordRequestDTO resetPasswordRequestDTO, HttpServletResponse response) {
        try {
            userService.validateToken(resetPasswordRequestDTO);
            response.setHeader("Location", "http://localhost:4200/new-password/" + resetPasswordRequestDTO.getToken());
            response.setStatus(HttpStatus.FOUND.value());
        } catch (Exception tokenExpiredException) {
            response.setHeader("Location", "http://localhost:4200/tokenExpired");
            response.setStatus(HttpStatus.FOUND.value());
        }
    }

    @PostMapping("/password/new")
    public ResponseEntity<Void> saveNewPassword(@Valid @RequestBody NewPasswordRequestDTO newPasswordRequestDTO) {
        try {
            userService.saveNewPassword(newPasswordRequestDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception tokenExpiredException) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
