package com.projectone.api.controllers;

import com.projectone.api.dto.JWTResponse;
import com.projectone.api.dto.LoginCredentials;
import com.projectone.api.dto.User;
import com.projectone.api.services.UserService;
import com.projectone.security.JWTUtil;
import com.projectone.store.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthenticationController {

  private JWTUtil jwtUtil;
  private UserService userService;
  private AuthenticationManager authManager;

  @PostMapping("/registration")
  public ResponseEntity<JWTResponse> registerHandler(@RequestBody UserEntity user){
    userService.save(user);
    String token = jwtUtil.generateToken(User.convertEntityToDTO(user));
    return ResponseEntity.ok(new JWTResponse(token, user.getId(), user.getUsername(), user.getRoles()));
  }

  @PostMapping("/login")
  public ResponseEntity<JWTResponse> loginHandler(@RequestBody LoginCredentials body){
    try {
      Authentication authentication =
              authManager.authenticate(new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);

      User user = userService.getByUsername(body.getUsername());
      String token = jwtUtil.generateToken(user);

      return ResponseEntity.ok(new JWTResponse(token, user.getId(), user.getUsername(), user.getRoles()));

    }catch (AuthenticationException authExc){
      throw new RuntimeException("Invalid Login Credentials");
    }
  }
}
