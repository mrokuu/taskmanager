package com.example.taskmanager.login.component.security;

import com.example.taskmanager.application.component.CurrentUser;
import com.example.taskmanager.application.domain.User;
import com.example.taskmanager.login.component.tool.AuthoritiesConverter;
import com.example.taskmanager.login.dto.response.SecurityResponseDTO;
import com.example.taskmanager.login.repository.TaskRepository;
import com.example.taskmanager.login.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
@AllArgsConstructor

public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomAuthSuccessHandler.class);

    private final AuthoritiesConverter authoritiesConverter;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final CurrentUser currentUser;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        LOG.info("onAuthenticationSuccess - set status to HttpServletResponse.SC_OK");
        String responseBody = createResponse(authentication);

        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter responseWriter = response.getWriter();
        responseWriter.print(responseBody);
        responseWriter.flush();
    }

    private String createResponse(Authentication authentication) throws JsonProcessingException {

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            User user = userRepository.findByEmail(((UserDetails) authentication.getPrincipal()).getUsername());
            currentUser.setId(user.getId());
            setMaxFakeId(user);

            UserDetails userDetails = (UserDetails) principal;
            List<String> roles = authoritiesConverter.convert(userDetails.getAuthorities());
            SecurityResponseDTO securityDto = securityResponseDTO(userDetails, roles);

            String response = objectMapper.writeValueAsString(securityDto);
            LOG.info("createResponse: " + response);

            return response;
        }

        throw new IllegalArgumentException("Principal invalid");
    }


    private void setMaxFakeId(User user) {
        Long maxFakeId = taskRepository.maxFakeId(user.getId());
        currentUser.setFakeId(maxFakeId);
    }

    private SecurityResponseDTO securityResponseDTO(UserDetails userDetails, List<String> roles) {
        SecurityResponseDTO securityDto = new SecurityResponseDTO(userDetails.getUsername(), roles);
        return securityDto;
    }

}
