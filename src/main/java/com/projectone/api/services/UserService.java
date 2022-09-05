package com.projectone.api.services;

import com.projectone.api.dto.User;
import com.projectone.store.entities.UserEntity;

import java.util.List;

public interface UserService {
  List<User> getAllUsers();
  User save(UserEntity userEntity);
  User getUserByUsername(String username);
  void deleteUserById(Long userId);
}
