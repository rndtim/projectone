package com.projectone.api.controllers;

import com.projectone.api.dto.User;
import com.projectone.api.services.UserService;
import com.projectone.store.entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("users/registration")
  public String registration() {
    System.out.println("GetMapping to users/registration");
    return "registration";
  }

  @PostMapping("users/registration")
  public @ResponseBody User addNewUser(UserEntity userEntity) {
    return userService.save(userEntity);
  }

}
