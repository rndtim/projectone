package com.projectone.service;

import com.projectone.entities.UserEntity;
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

  public List<UserEntity> getAll() {
    return userRepository.findAll();
  }

  public User save(UserEntity userEntity) {
    try {
      if (userRepository.findUserByEmail(userEntity.getEmail()) != null) {
        System.out.println("This email is being already used");
        throw new Exception();
      }
      return User.toModel(userRepository.save(userEntity));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public User getUserById(Long userId) {
    return User.toModel(userRepository.getReferenceById(userId));
  }

  public void deleteUserById(Long userId) {
    userRepository.deleteById(userId);
  }
}
