package com.projectone.api.controllers;

import com.projectone.api.dto.User;
import com.projectone.api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/home")
    public ResponseEntity<UUID> saveUser(@RequestBody User user) {
        UUID userId = userService.save(user);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @GetMapping("/home")
    public User getUserInfo(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
