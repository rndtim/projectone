package com.projectone.controllers;

import com.projectone.model.UserModel;
import com.projectone.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity getAllUsers() {
    List<UserModel> userModelList = userService.getAll();
    return ResponseEntity.ok(userModelList);
  }

  @GetMapping("/{userId}")
  public Long getOneUserWithId(@PathVariable Long userId) {
    return userId;
  }
}
