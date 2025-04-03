package com.projectone.api.controllers;

import com.projectone.api.dto.JWTResponse;
import com.projectone.api.dto.LoginCredentials;
import com.projectone.api.dto.User;
import com.projectone.api.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<Boolean> registerNewUser(@RequestBody User user) {
        if (authService.register(user))
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody LoginCredentials loginCredentials) {
        JWTResponse jwtResponse = authService.login(loginCredentials);
        if (jwtResponse != null)
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
