package com.projectone.services;

import com.projectone.entities.UserEntity;
import com.projectone.dto.User;
import com.projectone.exceptions.NoSuchUser;
import com.projectone.exceptions.UserAlreadyExists;
import com.projectone.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll().stream().map(User::convertEntityToModel).collect(Collectors.toList());
  }

  public User save(UserEntity userEntity) {
    try {
      if (userRepository.findUserByEmail(userEntity.getEmail()) != null) {
        throw new UserAlreadyExists("This email is being already used");
      }
      return User.convertEntityToModel(userRepository.save(userEntity));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public User getUserById(Long userId) {
    try {
      if (!userRepository.existsById(userId)) throw new NoSuchUser("No user with this id");
      return User.convertEntityToModel(userRepository.getReferenceById(userId));
    } catch (NoSuchUser e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteUserById(Long userId) {
    userRepository.deleteById(userId);
  }
}
