package com.example.taskmanager.login.service;

import com.example.taskmanager.application.component.MD5Encoder;
import com.example.taskmanager.application.domain.User;
import com.example.taskmanager.login.component.email.EmailSender;
import com.example.taskmanager.login.dto.request.ResetPasswordRequestDTO;
import com.example.taskmanager.login.dto.request.TokenRequestDTO;
import com.example.taskmanager.login.dto.response.EmailResponseDTO;
import com.example.taskmanager.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EmailSender emailSender;
    private final MD5Encoder md5Encoder;
    public EmailResponseDTO saveTokenAndSendEmail(TokenRequestDTO tokenRequestDTO) {
        User user = userRepository.findByEmail(tokenRequestDTO.getEmail());
        validate(user, "User not found by email");
        user.generateToken();
        save(user);

        EmailResponseDTO emailDTO = emailSender.sendResetPasswordEmail(user);
        return emailDTO;
    }


    private void validate(User user, String message) {
        if (user == null) {
            throw new RuntimeException(message);
        }
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public void validateToken(ResetPasswordRequestDTO resetPasswordRequestDTO) {
        User user = userRepository.findByToken(resetPasswordRequestDTO.getToken());
        validate(user, "Token is invalid or expired");
    }
}
