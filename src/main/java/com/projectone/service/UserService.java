package com.projectone.service;

import com.projectone.model.UserModel;

import java.util.List;

public interface UserService {
  List<UserModel> getAll();
  UserModel save(UserModel userModel);
  void deleteUserById(Long userId);
}
