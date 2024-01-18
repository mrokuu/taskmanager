package com.example.taskmanager.login.controller;

import com.example.taskmanager.login.dto.response.SecurityResponseDTO;
import com.example.taskmanager.login.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    private final SecurityService securityService;


    @GetMapping("/browse")
    public ResponseEntity<SecurityResponseDTO> browse(Principal principal) {
        SecurityResponseDTO securityDto = securityService.createSecurityInfo(principal);
        return new ResponseEntity<>(securityDto, HttpStatus.OK);
    }
}
