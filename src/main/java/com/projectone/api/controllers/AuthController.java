package com.projectone.api.controllers;

import com.projectone.api.dto.LoginCredentials;
import com.projectone.api.dto.User;
import com.projectone.api.services.AuthService;
import com.projectone.api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<UUID> registerNewUser(@RequestBody User user) {
        UUID userId = userService.save(user);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginCredentials loginCredentials) {
        return new ResponseEntity<>(authService.login(loginCredentials), HttpStatus.OK);
    }
}
