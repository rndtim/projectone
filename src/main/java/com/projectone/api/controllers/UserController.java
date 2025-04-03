package com.projectone.api.controllers;

import com.projectone.api.dto.User;
import com.projectone.api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/home")
    public User getUserInfo(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
