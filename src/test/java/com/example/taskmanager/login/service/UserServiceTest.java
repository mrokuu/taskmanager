package com.example.taskmanager.login.service;

import com.example.taskmanager.application.component.MD5Encoder;
import com.example.taskmanager.application.domain.User;
import com.example.taskmanager.login.component.email.EmailSender;
import com.example.taskmanager.login.dto.request.NewPasswordRequestDTO;
import com.example.taskmanager.login.dto.request.ResetPasswordRequestDTO;
import com.example.taskmanager.login.dto.request.TokenRequestDTO;
import com.example.taskmanager.login.dto.response.EmailResponseDTO;
import com.example.taskmanager.login.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailSender emailSender;

    @Mock
    private MD5Encoder md5Encoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTokenAndSendEmail_ValidUser_EmailSent() {
        // Arrange
        TokenRequestDTO tokenRequestDTO = new TokenRequestDTO("test@example.com");
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
        when(emailSender.sendResetPasswordEmail(user)).thenReturn(new EmailResponseDTO("Email sent successfully"));

        // Act
        EmailResponseDTO emailResponseDTO = userService.saveTokenAndSendEmail(tokenRequestDTO);

        // Assert
        assertNotNull(emailResponseDTO);
//        assertEquals("Email sent successfully", emailResponseDTO.getMessage());
        assertNotNull(user.getToken());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void saveTokenAndSendEmail_UserNotFound_ExceptionThrown() {
        // Arrange
        TokenRequestDTO tokenRequestDTO = new TokenRequestDTO("nonexistent@example.com");

        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.saveTokenAndSendEmail(tokenRequestDTO));
        verify(userRepository, never()).save(any());
        verify(emailSender, never()).sendResetPasswordEmail(any());
    }

    @Test
    void validateToken_ValidToken_NoExceptionThrown() {
        // Arrange
        ResetPasswordRequestDTO resetPasswordRequestDTO = new ResetPasswordRequestDTO("validToken");
        User user = new User();
        user.setToken("validToken");

        when(userRepository.findByToken("validToken")).thenReturn(user);

        // Act & Assert
        assertDoesNotThrow(() -> userService.validateToken(resetPasswordRequestDTO));
    }

    @Test
    void validateToken_InvalidToken_ExceptionThrown() {
        // Arrange
        ResetPasswordRequestDTO resetPasswordRequestDTO = new ResetPasswordRequestDTO("invalidToken");

        when(userRepository.findByToken("invalidToken")).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.validateToken(resetPasswordRequestDTO));
    }


    @Test
    void saveNewPassword_PasswordMismatch_ExceptionThrown() {
        // Arrange
        NewPasswordRequestDTO newPasswordRequestDTO = new NewPasswordRequestDTO("validToken", "newPassword", "differentPassword");
        User user = new User();
        user.setToken("validToken");

        when(userRepository.findByToken("validToken")).thenReturn(user);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.saveNewPassword(newPasswordRequestDTO));
        verify(md5Encoder, never()).getMD5Hash(any());
        verify(userRepository, never()).save(any());
    }
}