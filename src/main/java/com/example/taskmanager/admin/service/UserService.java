package com.example.taskmanager.admin.service;

import com.example.application.component.MD5Encoder;
import com.example.application.domain.Role;
import com.example.application.domain.RoleEnum;
import com.example.application.domain.User;
import com.example.taskmanager.admin.dto.request.UserRequestDTO;
import com.example.taskmanager.admin.repository.RoleRepository;
import com.example.taskmanager.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
}
