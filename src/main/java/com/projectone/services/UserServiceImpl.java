package com.projectone.services;

import com.projectone.entities.UserEntity;
import com.projectone.dto.User;
import com.projectone.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll().stream().map(User::convertEntityToModel).collect(Collectors.toList());
  }

  public User save(UserEntity userEntity) {
    try {
      if (userRepository.findUserByEmail(userEntity.getEmail()) != null) {
        System.out.println("This email is being already used");
        throw new Exception();
      }
      return User.convertEntityToModel(userRepository.save(userEntity));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public User getUserById(Long userId) {
    return User.convertEntityToModel(userRepository.getReferenceById(userId));
  }

  public void deleteUserById(Long userId) {
    userRepository.deleteById(userId);
  }
}
