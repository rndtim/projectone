package com.projectone.api.controllers;

import com.projectone.api.dto.User;
import com.projectone.api.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

  private UserService userService;

  @GetMapping("/home")
  public User getUserInfo() {
    String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return userService.getByUsername(username);
  }
}
