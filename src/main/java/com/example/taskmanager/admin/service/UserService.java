package com.example.taskmanager.admin.service;

import com.example.taskmanager.application.component.CurrentUser;
import com.example.taskmanager.application.component.MD5Encoder;
import com.example.taskmanager.application.domain.Role;
import com.example.taskmanager.application.domain.RoleEnum;
import com.example.taskmanager.application.domain.User;
import com.example.taskmanager.admin.dto.request.UserRequestDTO;
import com.example.taskmanager.admin.repository.RoleRepository;
import com.example.taskmanager.admin.repository.UserRepository;
import com.example.taskmanager.application.exception.UserInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CurrentUser currentUser;

    private final MD5Encoder md5Encoder;





    public void saveUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setPassword(md5Encoder.getMD5Hash(userRequestDTO.getNewPassword()));
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setProfileName(userRequestDTO.getProfileName());
        user.setEmail(userRequestDTO.getEmail());
        user.setActive(true);

        Role role = roleRepository.findByRoleName(RoleEnum.ROLE_ADMIN.getValue());
        user.getRoles().add(role);

        userRepository.save(user);
    }

    public void updateUser(UserRequestDTO userRequestDTO) throws UserInputException {

        User user = userRepository.findById(currentUser.getId()).orElseThrow(userNotFound());

        if (userRequestDTO.isPasswordChangeDetected()) {
            validatePasswordEquality(userRequestDTO, user);
            user.setPassword(md5Encoder.getMD5Hash(userRequestDTO.getNewPassword()));
        }

        user.setEmail(userRequestDTO.getEmail());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setProfileName(userRequestDTO.getProfileName());

        userRepository.save(user);
    }





    private void validatePasswordEquality(UserRequestDTO userRequestDTO, User user) throws UserInputException {
        if (!user.getPassword().equals(md5Encoder.getMD5Hash(userRequestDTO.getOldPassword()))) {
            throw new UserInputException("Old password doesn't match");
        }
    }
    private boolean hasBasicRole(Role role) {
        boolean basicRole = Arrays.stream(
                        new String[] { RoleEnum.ROLE_ADMIN.getValue(), RoleEnum.ROLE_USER.getValue() })
                .anyMatch(role.getRoleName()::equalsIgnoreCase);

        return basicRole;
    }

    private Supplier<? extends RuntimeException> userNotFound() {
        return () -> new RuntimeException("User not found");
    }
}
