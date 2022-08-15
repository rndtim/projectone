package com.projectone.service;

import com.projectone.model.UserModel;
import com.projectone.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserModel> getAll() {
    return userRepository.findAll();
  }

  public UserModel save(UserModel userModel) {
    return userRepository.save(userModel);
  }

  public void deleteUserById(Long userId) {
    userRepository.deleteById(userId);
  }
}
