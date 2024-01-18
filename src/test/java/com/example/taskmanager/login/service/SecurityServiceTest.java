package com.example.taskmanager.login.service;

import com.example.taskmanager.login.component.tool.AuthoritiesConverter;
import com.example.taskmanager.login.dto.response.SecurityResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SecurityServiceTest {

    @Mock
    private AuthoritiesConverter authoritiesConverter;

    @InjectMocks
    private SecurityService securityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSecurityInfo_ValidPrincipal_ReturnsSecurityResponseDTO() {
        // Arrange
        Principal principal = createMockPrincipal("user@example.com", Collections.singletonList("ROLE_USER"));
        UserDetails userDetails = createUserDetails("user@example.com", "password", Collections.singletonList("ROLE_USER"));

        when(authoritiesConverter.convert(userDetails.getAuthorities())).thenReturn(Collections.singletonList("USER"));

        // Act
        SecurityResponseDTO securityResponseDTO = securityService.createSecurityInfo(principal);

        // Assert
        assertEquals("user@example.com", securityResponseDTO.getEmail());
        assertEquals(Collections.singletonList("USER"), securityResponseDTO.getRoles());
    }

    private Principal createMockPrincipal(String email, List<String> roles) {
        UsernamePasswordAuthenticationToken authenticationToken = mock(UsernamePasswordAuthenticationToken.class);
        UserDetails userDetails = createUserDetails(email, "password", roles);

        when(authenticationToken.getPrincipal()).thenReturn(userDetails);

        return authenticationToken;
    }

    private UserDetails createUserDetails(String email, String password, List<String> roles) {
        return new User(email, password, Collections.emptyList());
    }
}