package com.projectone.service;

import com.projectone.entities.UserEntity;
import com.projectone.model.User;

import java.util.List;

public interface UserService {
  List<UserEntity> getAll();
  User save(UserEntity userEntity);
  User getUserById(Long userId);
  void deleteUserById(Long userId);
}
