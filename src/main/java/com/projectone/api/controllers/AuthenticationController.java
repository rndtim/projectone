package com.projectone.api.controllers;

import com.projectone.api.dto.LoginCredentials;
import com.projectone.api.dto.User;
import com.projectone.api.services.CustomUserDetailsService;
import com.projectone.api.services.UserService;
import com.projectone.security.JWTUtil;
import com.projectone.store.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthenticationController {

  private JWTUtil jwtUtil;
  private UserService userService;
  private AuthenticationManager authManager;
  private CustomUserDetailsService userDetailsService;

  @PostMapping("/registration")
  public Map<String, Object> registerHandler(@RequestBody UserEntity user){
    userService.save(user);
    String token = jwtUtil.generateToken(User.convertEntityToModel(user));
    return Collections.singletonMap("jwt-token", token);
  }

  @PostMapping("/login")
  public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
    try {
      UsernamePasswordAuthenticationToken authInputToken =
              new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());

      authManager.authenticate(authInputToken);
      User user = userService.getByUsername(body.getUsername());
      String token = jwtUtil.generateToken(user);

      return Collections.singletonMap("jwt-token", token);
    }catch (AuthenticationException authExc){
      throw new RuntimeException("Invalid Login Credentials");
    }
  }
}
