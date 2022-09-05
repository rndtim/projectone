package com.projectone.api.controllers;

import com.projectone.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth/")
@RequiredArgsConstructor
public class AuthenticationController {
  private final UserService userService;

//  @PostMapping("/login")
//  public ResponseEntity login(@RequestParam String username, @RequestParam String password) {
//
//  }
}
