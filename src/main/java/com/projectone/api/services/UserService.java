package com.projectone.api.services;

import com.projectone.api.dto.User;
import com.projectone.store.entities.UserEntity;

import java.util.List;

public interface UserService {
  List<User> getAllUsers();
  void save(UserEntity userEntity);
  User getByUsername(String username);
  void deleteUserById(Long userId);
}
