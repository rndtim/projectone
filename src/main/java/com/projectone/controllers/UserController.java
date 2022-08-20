package com.projectone.controllers;

import com.projectone.dto.User;
import com.projectone.entities.UserEntity;
import com.projectone.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{userId}")
  public User getOneUserWithId(@PathVariable Long userId) {
    return userService.getUserById(userId);
  }

  @PostMapping
  public User addNewUser(@RequestBody UserEntity userEntity) {
    return userService.save(userEntity);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity deleteUserById(@PathVariable Long userId) {
    userService.deleteUserById(userId);
    return ResponseEntity.ok("User has been deleted");
  }
}
