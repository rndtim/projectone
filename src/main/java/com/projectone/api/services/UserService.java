package com.projectone.api.services;

import com.projectone.api.dto.User;
import com.projectone.store.entities.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
  List<User> getAllUsers();
  UUID save(User user);
  User getByUsername(String username);
  void deleteUserById(UUID userId);
}
