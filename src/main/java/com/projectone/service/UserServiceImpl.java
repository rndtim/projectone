package com.projectone.service;

import com.projectone.model.User;
import com.projectone.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public void deleteUserById(Long userId) {
    userRepository.deleteById(userId);
  }
}
