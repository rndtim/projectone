package com.projectone.services;

import com.projectone.entities.UserEntity;
import com.projectone.dto.User;

import java.util.List;

public interface UserService {
  List<User> getAllUsers();
  User save(UserEntity userEntity);
  User getUserById(Long userId);
  void deleteUserById(Long userId);
}
