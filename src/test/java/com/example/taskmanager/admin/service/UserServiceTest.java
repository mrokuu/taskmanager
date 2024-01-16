package com.example.taskmanager.admin.service;


import com.example.taskmanager.admin.dto.request.UserRequestDTO;
import com.example.taskmanager.admin.repository.RoleRepository;
import com.example.taskmanager.admin.repository.UserRepository;
import com.example.taskmanager.application.component.CurrentUser;
import com.example.taskmanager.application.component.MD5Encoder;
import com.example.taskmanager.application.domain.Role;
import com.example.taskmanager.application.domain.RoleEnum;
import com.example.taskmanager.application.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.management.relation.RoleNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private CurrentUser currentUser;

    @Mock
    private MD5Encoder md5Encoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser_Success() {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setNewPassword("password123");
        userRequestDTO.setFirstName("John");
        userRequestDTO.setLastName("Doe");
        userRequestDTO.setProfileName("johndoe");
        userRequestDTO.setEmail("john.doe@example.com");

        Role role = new Role();
        role.setRoleName(RoleEnum.ROLE_ADMIN.getValue());

        when(roleRepository.findByRoleName(RoleEnum.ROLE_ADMIN.getValue())).thenReturn(role);
        when(md5Encoder.getMD5Hash("password123")).thenReturn("hashedPassword");

        // Act
        userService.saveUser(userRequestDTO);

        // Assert
        verify(userRepository, times(1)).save(any(User.class));
    }


    @Test
    public void testSaveUser_ExceptionDuringSave() {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setNewPassword("password123");
        userRequestDTO.setFirstName("John");
        userRequestDTO.setLastName("Doe");
        userRequestDTO.setProfileName("johndoe");
        userRequestDTO.setEmail("john.doe@example.com");

        Role role = new Role();
        role.setRoleName(RoleEnum.ROLE_ADMIN.getValue());

        when(roleRepository.findByRoleName(RoleEnum.ROLE_ADMIN.getValue())).thenReturn(role);
        when(md5Encoder.getMD5Hash("password123")).thenReturn("hashedPassword");
        doThrow(RuntimeException.class).when(userRepository).save(any(User.class));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.saveUser(userRequestDTO));
    }
}