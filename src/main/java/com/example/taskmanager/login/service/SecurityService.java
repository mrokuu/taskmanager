package com.example.taskmanager.login.service;

import com.example.taskmanager.login.component.tool.AuthoritiesConverter;
import com.example.taskmanager.login.dto.response.SecurityResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AuthoritiesConverter authoritiesConverter;

    public SecurityResponseDTO createSecurityInfo(Principal principal) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        UserDetails userDetails = (UserDetails) usernamePasswordAuthenticationToken.getPrincipal();

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<String> roles = authoritiesConverter.convert(authorities);
        SecurityResponseDTO securityDto = new SecurityResponseDTO(userDetails.getUsername(), roles);

        return securityDto;
    }

}
