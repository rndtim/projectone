package com.projectone.controllers;

import com.projectone.entities.UserEntity;
import com.projectone.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity getAllUsers() {
    return ResponseEntity.ok(userService.getAll());
  }

  @GetMapping("/{userId}")
  public ResponseEntity getOneUserWithId(@PathVariable Long userId) {
    return ResponseEntity.ok(userService.getUserById(userId));
  }

  @PostMapping
  public ResponseEntity addNewUser(@RequestBody UserEntity userEntity) {
    return ResponseEntity.ok(userService.save(userEntity));
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity deleteUserById(@PathVariable Long userId) {
    userService.deleteUserById(userId);
    return ResponseEntity.ok("User has been deleted");
  }
}
