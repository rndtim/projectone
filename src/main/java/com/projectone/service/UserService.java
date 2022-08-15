package com.projectone.service;

import com.projectone.model.User;

import java.util.List;

public interface UserService {
  List<User> getAll();
  User save(User user);
  void deleteUserById(Long userId);
}
