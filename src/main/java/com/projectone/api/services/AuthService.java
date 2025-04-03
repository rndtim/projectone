package com.projectone.api.services;

import com.projectone.api.dto.LoginCredentials;
import com.projectone.api.dto.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;

    public boolean register(@RequestBody User user) {
        return userService.save(user) != null;
    }

    public String login(LoginCredentials loginCredentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginCredentials.getUsername(), loginCredentials.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authentication.getName());
        }
        return "Not logged in";
    }
}
